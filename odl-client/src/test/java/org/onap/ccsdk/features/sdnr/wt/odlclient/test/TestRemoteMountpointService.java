/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.test;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Future;

import org.junit.Test;
import org.onap.ccsdk.features.sdnr.wt.odlclient.config.RemoteOdlConfig.AuthMethod;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlRpcObjectMapperXml;
import org.onap.ccsdk.features.sdnr.wt.odlclient.remote.RemoteMountPoint;
import org.onap.ccsdk.features.sdnr.wt.odlclient.restconf.RestconfHttpClient;
import org.opendaylight.mdsal.binding.api.MountPoint;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.LedControlInputBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.LedControlOutput;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.OrgOpenroadmDeviceService;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.led.control.input.equipment.entity.ShelfBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.led.control.input.equipment.entity.CircuitPackBuilder;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRemoteMountpointService {

    private static final Logger LOG = LoggerFactory.getLogger(TestRemoteMountpointService.class);

    private static final String DEVICEID = "onapextroadma1";
    private static final String ODL_USERNAME = "admin";
    private static final String ODL_PASSWD = "Kp8bJ4SXszM0WXlhak3eHlcse2gAw84vaoGGmJvUy2U";
    private static final String BASEURL = "http://172.20.0.3:8181";

    @Test
    public void test() throws Exception {

        RestconfHttpClient restClient = new RestconfHttpClient(BASEURL, false, AuthMethod.BASIC,
                ODL_USERNAME, ODL_PASSWD);
        MountPoint mountPoint = new RemoteMountPoint(restClient, DEVICEID);
        final Optional<RpcConsumerRegistry> service = mountPoint.getService(RpcConsumerRegistry.class);
        if (!service.isPresent()) {
            LOG.error("Failed to get RpcService for node {}", DEVICEID);
        }
        final OrgOpenroadmDeviceService rpcService = service.get()
                .getRpcService(OrgOpenroadmDeviceService.class);
        final LedControlInputBuilder builder = new LedControlInputBuilder();
        builder.setEnabled(true).setEquipmentEntity(new CircuitPackBuilder().setCircuitPackName("1/0").build());
        final Future<RpcResult<LedControlOutput>> output = rpcService
                .ledControl(builder.build());
        LOG.info("{}",output);
    }
    @Test
    public void testRpcDeserializer() throws IOException {
        OdlRpcObjectMapperXml mapper = new OdlRpcObjectMapperXml();
       LedControlOutput output = mapper.readValue("<output xmlns=\"http://org/openroadm/device\">\n" +
                "    <status>Successful</status>\n" +
                "    <status-message>test</status-message>\n" +
                "</output>",LedControlOutput.class);
        LOG.info("{}",output);
    }
}
