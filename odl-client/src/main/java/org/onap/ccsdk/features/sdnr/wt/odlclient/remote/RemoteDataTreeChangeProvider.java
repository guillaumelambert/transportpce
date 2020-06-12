/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.remote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.SdnrNotification;
import org.onap.ccsdk.features.sdnr.wt.odlclient.remote.RemoteListenerRegistration.CloseCallback;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.binding.api.DataTreeChangeListener;
import org.opendaylight.mdsal.binding.api.DataTreeIdentifier;
import org.opendaylight.mdsal.binding.api.DataTreeModification;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yangtools.concepts.ListenerRegistration;

public class RemoteDataTreeChangeProvider<T extends Node> {

    private final RestconfHttpClient client;
    private final List<RemoteListenerRegistration<? extends DataTreeChangeListener<Node>, T>> listeners;

    public RemoteDataTreeChangeProvider(RestconfHttpClient restClient) {
        this.client = restClient;
        this.listeners = new ArrayList<>();
    }

    public <L extends DataTreeChangeListener<Node>> @NonNull ListenerRegistration<L> register(
            @NonNull DataTreeIdentifier<T> treeId, @NonNull DataTreeChangeListener<T> listener) {
        RemoteListenerRegistration<L, T> reg = new RemoteListenerRegistration<>(listener, new CloseCallback<L, T>() {


        });
        this.listeners.add(reg);
        return reg;
    }

    public void onControllerNotification(SdnrNotification notification) {
        for (RemoteListenerRegistration<? extends DataTreeChangeListener<Node>, T> reg : this.listeners) {
            @NonNull
            Collection<DataTreeModification<Node>> changes = Arrays
                    .asList(this.createModification(notification));
            reg.getInstance().onDataTreeChanged(changes);
        }
    }

    private DataTreeModification<Node> createModification(SdnrNotification notification) {
        // TODO Auto-generated method stub
        return null;
    }
}
