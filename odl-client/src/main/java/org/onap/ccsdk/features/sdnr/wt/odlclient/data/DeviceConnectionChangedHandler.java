/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNode;

public interface DeviceConnectionChangedHandler {
    void onRemoteDeviceConnected(final String nodeId, NetconfNode nNode);
    void onRemoteDeviceDisConnected(final String nodeId);
    void onRemoteDeviceUnableToConnect(final String nodeId);
    void onRemoteDeviceConnecting(final String nodeId);
}
