/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.DataTreeChangeListener;
import org.opendaylight.mdsal.binding.api.DataTreeIdentifier;
import org.opendaylight.mdsal.binding.api.MountPoint;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yangtools.concepts.ListenerRegistration;

public interface RemoteOpendaylightClient {

    DataBroker getRemoteDeviceDataBroker(String nodeId);

    DataBroker getRemoteDataBroker();

    boolean isDevicePresent(String nodeId);

    MountPoint getMountPoint(String deviceId);

    <L extends DataTreeChangeListener<Node>> @NonNull ListenerRegistration<L> registerDataTreeChangeListener(
            @NonNull DataTreeIdentifier<Node> treeId, @NonNull L listener);

    boolean isEnabled();
}