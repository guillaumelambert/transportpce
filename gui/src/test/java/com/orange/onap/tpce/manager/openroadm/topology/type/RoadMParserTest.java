package com.orange.onap.tpce.manager.openroadm.topology.type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.helper.NetworkHelper;
import com.orange.onap.tpce.manager.helper.RoadMHelper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.Networks;
import com.orange.onap.tpce.manager.feature.openroadm.topology.service.TopologyService;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNetwork;
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
public class RoadMParserTest {


    @Autowired
    private TopologyService networkService;

    private RoadMNetwork roadMNetwork;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws IOException {
        Networks rawNetwork = objectMapper.readValue(NetworkHelper.getRoadMContent(),
                Networks.class);

        roadMNetwork = networkService.parseNetworks(rawNetwork.getNetworks()).getRoadMNetwork();

    }

    @Test
    public void networkParserTest() throws IOException {

        try {

            Assert.assertNotNull(roadMNetwork);

            Assert.assertTrue(roadMNetwork.getNetworkType().containsKey(RoadMHelper.NETWORK_TYPE));
            Assert.assertTrue(roadMNetwork.getNetworkId().equals(RoadMHelper.NETWORK_ID));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }


    @Test
    public void nodeParserTest() throws IOException {

        try {

            Assert.assertNotNull(roadMNetwork);

            Assert.assertTrue(roadMNetwork.getNetworkId().equals(RoadMHelper.NETWORK_ID));
            Assert.assertTrue(roadMNetwork.getNetworkType().containsKey(RoadMHelper.NETWORK_TYPE));

            Assert.assertNotNull(roadMNetwork.getNodes());
            Assert.assertFalse(roadMNetwork.getNodes().isEmpty());
            Assert.assertTrue(roadMNetwork.getNodes().get(0).getNodeId().equals(RoadMHelper.NODE_ID));
            Assert.assertTrue(roadMNetwork.getNodes().get(0).getNodeType().equals(RoadMHelper.NODE_TYPE));

            Assert.assertNotNull(roadMNetwork.getNodes().get(0).getSupportingNode());
            Assert.assertTrue(roadMNetwork.getNodes().get(0).getSupportingNode().get(0).getNodeRef().equals(RoadMHelper.SUPPORTING_NODE_REF));
            Assert.assertTrue(roadMNetwork.getNodes().get(0).getSupportingNode().get(0).getNetworkRef().equals(RoadMHelper.SUPPORTING_NETWORK_REF));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }
}
