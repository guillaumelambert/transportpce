/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.remote;

import java.util.Optional;

import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.odlclient.remote.mountpoint.RemoteRpcConsumerRegistry;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.binding.api.BindingService;
import org.opendaylight.mdsal.binding.api.MountPoint;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

public class RemoteMountPoint implements MountPoint{

    private final RestconfHttpClient restClient;
    private final String nodeId;

    public RemoteMountPoint(RestconfHttpClient client, String nodeId) {
        this.restClient =client;
        this.nodeId = nodeId;
    }
    @Override
    public @NonNull InstanceIdentifier<?> getIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends BindingService> @NonNull Optional<T> getService(@NonNull Class<T> service) {

        if(service.getClass().equals(RpcConsumerRegistry.class)){
            return (@NonNull Optional<T>) Optional.of(new RemoteRpcConsumerRegistry(this.restClient));
        }
        // TODO Auto-generated method stub
        return null;
    }

}
