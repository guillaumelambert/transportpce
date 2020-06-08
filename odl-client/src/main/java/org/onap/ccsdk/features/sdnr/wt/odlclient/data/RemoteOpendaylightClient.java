/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPoint;

public interface RemoteOpendaylightClient {

    DataBroker getRemoteDeviceDataBroker(String nodeId);

    DataBroker getRemoteDataBroker();

    boolean isDevicePresent(String nodeId);

    MountPoint getMountPoint(String deviceId);
}