package com.orange.onap.tpce.manager.openroadm.topology.type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.helper.ClliHelper;
import com.orange.onap.tpce.manager.helper.NetworkHelper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.Networks;
import com.orange.onap.tpce.manager.feature.openroadm.topology.service.TopologyService;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNetwork;
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
public class ClliParserTest {


    @Autowired
    private TopologyService topologyService;

    private ClliNetwork clliNetwork;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws IOException {
        Networks networks = objectMapper.readValue(NetworkHelper.getClliContent(),
                Networks.class);

        clliNetwork = topologyService.parseNetworks(networks.getNetworks()).getClliNetwork();

    }

    @Test
    public void networkParserTest() throws IOException {

        try {

            Assert.assertNotNull(clliNetwork);

            Assert.assertTrue(clliNetwork.getNetworkType().containsKey(ClliHelper.NETWORK_TYPE));
            Assert.assertTrue(clliNetwork.getNetworkId().equals(ClliHelper.NETWORK_ID));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }

    @Test
    public void nodeParserTest() throws IOException {

        try {

            Assert.assertNotNull(clliNetwork);

            Assert.assertTrue(clliNetwork.getNetworkId().equals(ClliHelper.NETWORK_ID));
            Assert.assertTrue(clliNetwork.getNetworkType().containsKey(ClliHelper.NETWORK_TYPE));

            Assert.assertNotNull(clliNetwork.getNodes());
            Assert.assertFalse(clliNetwork.getNodes().isEmpty());
            Assert.assertTrue(clliNetwork.getNodes().get(0).getNodeId().equals(ClliHelper.NODE_ID));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail();
        }
    }
}
