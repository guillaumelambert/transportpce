/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.onap.ccsdk.features.sdnr.wt.odlclient.config.RemoteOdlConfig.AuthMethod;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.OrgOpenroadmDevice;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Info;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

public class TestIifToUri {

        private static final String ROADMAA_INFO = "/rests/data/network-topology:network-topology/topology=topology-netconf"
                + "/node=roadmaa/yang-ext:mount/org-openroadm-device:org-openroadm-device/info";
    private static final String ROADMAA_NODEID = "roadmaa";

    @Test
    public void test1() throws Exception {

        TestRestconfHttpClient restconfClient = new TestRestconfHttpClient("http://localhost:8181/", false, AuthMethod.BASIC,"","");
        String uri;
        uri = restconfClient.getRfc8040UriFromIif(LogicalDatastoreType.CONFIGURATION,
                InstanceIdentifier.create(OrgOpenroadmDevice.class).child(Info.class), ROADMAA_NODEID);
        assertEquals(ROADMAA_INFO, uri);
    }

    private class TestRestconfHttpClient extends RestconfHttpClient {

        TestRestconfHttpClient(String base, boolean trustAllCerts, AuthMethod authMethod,
                String username, String password) throws Exception {
            super(base, trustAllCerts,authMethod, username, password);
            // TODO Auto-generated constructor stub
        }

        @Override
        public <T extends DataObject> String getRfc8040UriFromIif(LogicalDatastoreType storage,
                InstanceIdentifier<T> instanceIdentifier, String nodeId) throws ClassNotFoundException,
                NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
            // TODO Auto-generated method stub
            return super.getRfc8040UriFromIif(storage, instanceIdentifier, nodeId);
        }

    }
}
