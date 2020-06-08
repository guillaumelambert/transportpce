/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.RemoteOpendaylightClient;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.onap.ccsdk.features.sdnr.wt.odlclient.ws.SdnrWebsocketCallback;
import org.onap.ccsdk.features.sdnr.wt.odlclient.ws.SdnrWtWebsocket;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.DataTreeChangeListener;
import org.opendaylight.mdsal.binding.api.DataTreeIdentifier;
import org.opendaylight.mdsal.binding.api.MountPoint;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNodeConnectionStatus.ConnectionStatus;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.network.topology.topology.topology.types.TopologyNetconf;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.NodeKey;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpendaylightClient implements AutoCloseable, RemoteOpendaylightClient {

    private static final Logger LOG = LoggerFactory.getLogger(OpendaylightClient.class);
    private static final InstanceIdentifier<Topology> NETCONF_TOPO_IID = InstanceIdentifier
            .create(NetworkTopology.class).child(Topology.class,
                    new TopologyKey(new TopologyId(TopologyNetconf.QNAME.getLocalName())));
    private static final boolean TRUSTALLCERTS = false;
    private final RestconfHttpClient restClient;
    private final WebSocketClient wsClient;
    private final SdnrWebsocketCallback wsCallback = new SdnrWebsocketCallback() {

        @Override
        public void onMessageReceived(String msg) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onError(Throwable cause) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onDisconnect(SdnrWtWebsocket socket) {
            // TODO Auto-generated method stub

        }
    };
    private final DataBroker dataBroker;
    private final Map<String, DataBroker> deviceDataBrokers;

    public OpendaylightClient() throws Exception {
        this("http://sdnr:8181", "ws://sdnr:8181/websocket");
    }

    public OpendaylightClient(String baseUrl, @Nullable String wsUrl) throws Exception {
        this.restClient = new RestconfHttpClient(baseUrl, TRUSTALLCERTS);
        this.wsClient = wsUrl == null ? null : new WebSocketClient();
        if (this.wsClient != null) {
            this.wsClient.start();
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            this.wsClient.connect(new SdnrWtWebsocket(this.wsCallback), new URI(wsUrl), request);
        }
        this.dataBroker = new RemoteDataBroker(this.restClient);
        this.deviceDataBrokers = new HashMap<>();
    }
    // @Override
    // public <T extends DataObject> Optional<T> getConfigData(InstanceIdentifier<T>
    // iif,String nodeId) throws IOException, ClassNotFoundException,
    // NoSuchFieldException, SecurityException, IllegalArgumentException,
    // IllegalAccessException{
    // return this.restClient.readConfig(iif,nodeId);
    // }
    // @Override
    // public <T extends DataObject> Optional<T>
    // getOperationalData(InstanceIdentifier<T> iif,String nodeId) throws
    // IOException, ClassNotFoundException, NoSuchFieldException, SecurityException,
    // IllegalArgumentException, IllegalAccessException{
    // return this.restClient.readOperational(iif,nodeId);
    // }

    @Override
    public void close() throws Exception {
        if (this.wsClient != null) {
            if (!this.wsClient.isStopped()) {
                this.wsClient.stop();
            }
        }
    }

    @Override
    public boolean isDevicePresent(String nodeId) {

        InstanceIdentifier<NetconfNode> iif = NETCONF_TOPO_IID
                .child(Node.class, new NodeKey(new NodeId(nodeId))).augmentation(NetconfNode.class);
        try {
            @NonNull
            Optional<NetconfNode> node = this.restClient.read(LogicalDatastoreType.OPERATIONAL, iif)
                    .get();
            return node.isPresent() ? node.get().getConnectionStatus() == ConnectionStatus.Connected
                    : false;
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException | IOException | InterruptedException
                | ExecutionException e) {
            LOG.warn("unable to read netconfnode {}:", nodeId, e);

        }
        return false;
    }

    @Override
    public DataBroker getRemoteDeviceDataBroker(String nodeId) {
        DataBroker broker = this.deviceDataBrokers.get(nodeId);
        if (broker == null) {
            broker = new RemoteDeviceDataBroker(this.restClient, nodeId);
            this.deviceDataBrokers.put(nodeId, broker);
        }
        return broker;
    }

    @Override
    public DataBroker getRemoteDataBroker() {
        return this.dataBroker;
    }

    @Override
    public MountPoint getMountPoint(String deviceId) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <T extends DataObject, L extends DataTreeChangeListener<T>> @NonNull ListenerRegistration<L> registerDataTreeChangeListener(
            @NonNull DataTreeIdentifier<T> treeId, @NonNull L listener) {
        // TODO Auto-generated method stub
        return null;
    }

}
