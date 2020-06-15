/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.eclipse.jdt.annotation.Nullable;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.DeviceConnectionChangedHandler;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.SdnrNotification;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.notifications.AttributeValueChangedNotification;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.notifications.ObjectCreationNotification;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.notifications.ObjectDeletionNotification;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNodeConnectionStatus.ConnectionStatus;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.network.topology.topology.topology.types.TopologyNetconf;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteDeviceConnectionChangeProvider {

    private static final InstanceIdentifier<Topology> NETCONF_TOPO_IID = InstanceIdentifier
            .create(NetworkTopology.class).child(Topology.class,
                    new TopologyKey(new TopologyId(TopologyNetconf.QNAME.getLocalName())));

    private static final Logger LOG = LoggerFactory
            .getLogger(RemoteDeviceConnectionChangeProvider.class);
    private final RestconfHttpClient client;
    private final List<DeviceConnectionChangedHandler> listeners;

    public RemoteDeviceConnectionChangeProvider(RestconfHttpClient restClient) {
        this.client = restClient;
        this.listeners = new ArrayList<>();
    }

    public void onControllerNotification(SdnrNotification notification) {
        if (notification.isControllerNotification()) {
            if (notification instanceof ObjectCreationNotification) {
                String nodeId = ((ObjectCreationNotification) notification).getObjectId();
                this.handleChange(nodeId);

            } else if (notification instanceof ObjectDeletionNotification) {
                String nodeId = ((ObjectCreationNotification) notification).getObjectId();
                this.pushDisconnect(nodeId);

            } else if (notification instanceof AttributeValueChangedNotification) {
                AttributeValueChangedNotification n = (AttributeValueChangedNotification) notification;
                if (n.getAttributeName().equals("ConnectionStatus")) {
                    String nodeId = n.getObjectId();
                    this.handleChange(nodeId);
                }
            } else {
                LOG.debug("notification {} ignored by type", notification);
            }
        } else {
            LOG.debug("notification {} ignored", notification);
        }
    }

    private void handleChange(String nodeId) {
        InstanceIdentifier<NetconfNode> nodeIif = NETCONF_TOPO_IID.child(Node.class)
                .augmentation(NetconfNode.class);
        try {
            Optional<NetconfNode> nNode = this.client.read(LogicalDatastoreType.OPERATIONAL, nodeIif)
                    .get();
            this.handleChange(nodeId, nNode.isPresent() ? nNode.get() : null);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException | InterruptedException
                | ExecutionException | IOException e) {
            LOG.warn("problem reading netconfnode for {}: ", nodeId, e);
        }

    }

    private void handleChange(String nodeId, NetconfNode nNode) {
        @Nullable
        ConnectionStatus csts = nNode.getConnectionStatus();
        if (csts != null) {
            if (csts == ConnectionStatus.Connected) {
                this.pushConnect(nodeId, nNode);
            } else if (csts == ConnectionStatus.Connecting) {
                this.pushConnecting(nodeId, nNode);
            } else if (csts == ConnectionStatus.UnableToConnect) {
                this.pushUnableToConnect(nodeId, nNode);
            }

        } else {
            LOG.warn("unable to handle node {} without connection status", nodeId);
        }
    }

    private void pushConnect(String nodeId, NetconfNode nNode) {
        for (DeviceConnectionChangedHandler listener : this.listeners) {
            listener.onRemoteDeviceConnected(nodeId, nNode);
        }
    }

    private void pushConnecting(String nodeId, NetconfNode nNode) {
        for (DeviceConnectionChangedHandler listener : this.listeners) {
            listener.onRemoteDeviceConnecting(nodeId);
        }
    }

    private void pushUnableToConnect(String nodeId, NetconfNode nNode) {
        for (DeviceConnectionChangedHandler listener : this.listeners) {
            listener.onRemoteDeviceUnableToConnect(nodeId);
        }
    }

    private void pushDisconnect(String nodeId) {
        for (DeviceConnectionChangedHandler listener : this.listeners) {
            listener.onRemoteDeviceDisConnected(nodeId);
        }
    }

    public void register(DeviceConnectionChangedHandler listener) {
        this.listeners.add(listener);
    }

    public void unregister(DeviceConnectionChangedHandler listener) {
        this.listeners.remove(listener);
    }

}
