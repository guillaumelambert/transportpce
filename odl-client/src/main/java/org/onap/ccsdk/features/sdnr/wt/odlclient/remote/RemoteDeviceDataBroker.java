/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.remote;

import com.google.common.util.concurrent.FluentFuture;
import java.io.IOException;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.odlclient.OpendaylightClient;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.binding.api.ReadWriteTransaction;
import org.opendaylight.mdsal.common.api.CommitInfo;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yangtools.util.concurrent.FluentFutures;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteDeviceDataBroker extends RemoteDataBroker {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteDeviceDataBroker.class);

    private final String nodeId;

    public RemoteDeviceDataBroker(RestconfHttpClient odlClient, String nodeId) {
        super(odlClient);
        this.nodeId = nodeId;
    }

    @Override
    public ReadTransaction newReadOnlyTransaction() {
        return new RemoteDeviceReadOnlyTransaction(this.remoteOdlClient, this.nodeId);
    }

    @Override
    public @NonNull ReadWriteTransaction newReadWriteTransaction() {
        return new RemoteDeviceReadWriteTransaction(this.remoteOdlClient, this.nodeId);
    }

    private static final class RemoteDeviceReadWriteTransaction implements ReadWriteTransaction {

        private final RestconfHttpClient client;
        private final String nodeId;

        private RemoteDeviceReadWriteTransaction(RestconfHttpClient remoteOdlClient, String nodeId) {
            this.client = remoteOdlClient;
            this.nodeId = nodeId;
        }

        @Override
        public boolean cancel() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public @NonNull FluentFuture<? extends @NonNull CommitInfo> commit() {
            CommitInfo result = new CommitInfo() {

            };
            return FluentFutures.immediateFluentFuture(result );
        }

        @Override
        public @NonNull Object getIdentifier() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public <T extends DataObject> void put(@NonNull LogicalDatastoreType store, @NonNull InstanceIdentifier<T> path,
                @NonNull T data) {
            // TODO Auto-generated method stub

        }

        @Override
        public <T extends DataObject> void mergeParentStructurePut(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<T> path, @NonNull T data) {
            // TODO Auto-generated method stub

        }

        @Override
        public <T extends DataObject> void merge(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<T> path, @NonNull T data) {
            // TODO Auto-generated method stub

        }

        @Override
        public <T extends DataObject> void mergeParentStructureMerge(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<T> path, @NonNull T data) {
            // TODO Auto-generated method stub

        }

        @Override
        public void delete(@NonNull LogicalDatastoreType store, @NonNull InstanceIdentifier<?> path) {
            try {
                this.client.delete(store, path, this.nodeId);
            } catch (SecurityException | IllegalArgumentException e) {
                LOG.warn("problem deleting {} in {} for node {}: ", path, store, this.nodeId, e);
            }
        }

        @Override
        public <T extends DataObject> @NonNull FluentFuture<Optional<T>> read(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<T> path) {
            try {
                return this.client.read(store, path, this.nodeId);
            } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException
                    | IllegalAccessException | IOException e) {
                return FluentFutures.immediateFailedFluentFuture(e);
            }
        }

        @Override
        public @NonNull FluentFuture<Boolean> exists(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<?> path) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    private static final class RemoteDeviceReadOnlyTransaction implements ReadTransaction {

        private final RestconfHttpClient client;
        private final String nodeId;

        private RemoteDeviceReadOnlyTransaction(RestconfHttpClient remoteOdlClient, String nodeId) {
            this.client = remoteOdlClient;
            this.nodeId = nodeId;
        }

        @Override
        public <T extends DataObject> @NonNull FluentFuture<Optional<T>> read(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<T> path) {
            try {
                return this.client.read(store, path, this.nodeId);
            } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException
                    | IllegalAccessException | IOException e) {
                return FluentFutures.immediateFailedFluentFuture(e);
            }
        }

        @Override
        public @NonNull FluentFuture<Boolean> exists(@NonNull LogicalDatastoreType store,
                @NonNull InstanceIdentifier<?> path) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public @NonNull Object getIdentifier() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void close() {
            // TODO Auto-generated method stub

        }

    }
}
