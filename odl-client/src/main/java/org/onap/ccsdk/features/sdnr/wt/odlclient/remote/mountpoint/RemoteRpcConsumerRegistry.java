/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.remote.mountpoint;

import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.binding.api.BindingService;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.yangtools.yang.binding.RpcService;

public class RemoteRpcConsumerRegistry implements RpcConsumerRegistry,BindingService {

    private RestconfHttpClient client;

    public RemoteRpcConsumerRegistry(RestconfHttpClient restClient) {
        this.client = restClient;
    }

    @Override
    public <T extends RpcService> @NonNull T getRpcService(@NonNull Class<T> serviceInterface) {
        return null;//new RemoteGeneriRpcService(this.client,serviceInterface);
    }

}
