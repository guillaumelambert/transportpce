package com.orange.onap.tpce.manager.openroadm.topology.type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.helper.NetworkHelper;
import com.orange.onap.tpce.manager.helper.DeviceHelper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.Networks;
import com.orange.onap.tpce.manager.feature.openroadm.topology.service.TopologyService;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNetwork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceParserTest {


    @Autowired
    private TopologyService topologyService;

    private DeviceNetwork deviceNetwork;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws IOException {
        Networks rawNetwork = objectMapper.readValue(NetworkHelper.getTopologyContent(),
                Networks.class);

        deviceNetwork = topologyService.parseNetworks(rawNetwork.getNetworks()).getDeviceNetwork();

    }

    @Test
    public void networkParserTest() throws IOException {

        try {

            Assert.assertNotNull(deviceNetwork);

            Assert.assertTrue(deviceNetwork.getNetworkType().containsKey(DeviceHelper.NETWORK_TYPE));
            Assert.assertTrue(deviceNetwork.getNetworkId().equals(DeviceHelper.NETWORK_ID));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    public void nodeParserTest() throws IOException {

        try {

            Assert.assertNotNull(deviceNetwork);

            Assert.assertNotNull(deviceNetwork.getNodes());
            Assert.assertFalse(deviceNetwork.getNodes().isEmpty());
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getNodeId().equals(DeviceHelper.NODE_ID));
            Assert.assertTrue(deviceNetwork.getNodes().get(1).getNodeId().equals(DeviceHelper.NODE_ID_2));
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getNodeType().equals(DeviceHelper.NODE_TYPE));


            Assert.assertNotNull(deviceNetwork.getNodes().get(0).getSupportingNode());
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getSupportingNode().get(0).getNodeRef().equals(DeviceHelper.SUPPORTING_NODE_REF));
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getSupportingNode().get(0).getNetworkRef().equals(DeviceHelper.SUPPORTING_NETWORK_REF));
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    public void nodeTerminationPointParserTest() {
        try {

            Assert.assertNotNull(deviceNetwork.getNodes().get(0).getTerminationPoints());
            Assert.assertFalse(deviceNetwork.getNodes().get(0).getTerminationPoints().isEmpty());
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getTerminationPoints().get(0).getTpId().equals(DeviceHelper.TP_ID));
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getTerminationPoints().get(0).getTpType().equals(DeviceHelper.TP_TYPE));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    public void nodeAttributesParserTest() {
        try {

            Assert.assertNotNull(deviceNetwork.getNodes().get(0).getAttributes());
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getAttributes().getDegreeNumber().equals(2));

            Assert.assertNotNull(deviceNetwork.getNodes().get(0).getAttributes().getWaveLengths());
            Assert.assertFalse(deviceNetwork.getNodes().get(0).getAttributes().getWaveLengths().isEmpty());
            Assert.assertTrue(deviceNetwork.getNodes().get(0).getAttributes().getWaveLengths().get(0).get("index").equals(DeviceHelper.WAVE_LENGTH_VALUE));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    public void linkParserTest() {
        try {

            Assert.assertNotNull(deviceNetwork);

            Assert.assertNotNull(deviceNetwork.getLinks());

            Assert.assertTrue(deviceNetwork.getLinks().get(0).getLinkId().equals(DeviceHelper.LINK_ID));
            Assert.assertTrue(deviceNetwork.getLinks().get(0).getLinkType().equals(DeviceHelper.LINK_TYPE));

            Assert.assertTrue(deviceNetwork.getLinks().get(0).getOppositeLinkId().equals(DeviceHelper.OPPOSITE_LINK_ID));

            Assert.assertTrue(deviceNetwork.getLinks().get(0).getSource().getNodeId().equals(DeviceHelper.SOURCE_NODE));
            Assert.assertTrue(deviceNetwork.getLinks().get(0).getSource().getTpId().equals(DeviceHelper.SOURCE_TP));

            Assert.assertTrue(deviceNetwork.getLinks().get(0).getDestination().getNodeId().equals(DeviceHelper.DESTINATION_NODE));
            Assert.assertTrue(deviceNetwork.getLinks().get(0).getDestination().getTpId().equals(DeviceHelper.DESTINATION_TP));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

}
