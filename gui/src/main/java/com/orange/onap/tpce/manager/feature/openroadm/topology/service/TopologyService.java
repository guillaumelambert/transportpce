package com.orange.onap.tpce.manager.feature.openroadm.topology.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.ParsedNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.RawNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.Network;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.SupportingNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.otn.OtnNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.enums.NetworkType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TopologyService {

    private ObjectMapper objectMapper;

    public TopologyService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ParsedNetwork parseNetworks(RawNetwork networks) throws IOException {
        ClliNetwork clliNetwork = null;
        DeviceNetwork deviceNetwork = null;
        RoadMNetwork roadMNetwork = null;
        OtnNetwork otnNetwork = null;

        for (Network network : networks.getNetwork()) {
            System.out.println(network.getNetworkType());
            if (network.getNetworkId().equals(NetworkType.ClliNetwork.getNetworkValue())) {
                clliNetwork = objectMapper.convertValue(network, new TypeReference<ClliNetwork>() {
                });

            } else if (network.getNetworkId().equals(NetworkType.OtnNetwork.getNetworkValue())) {
                otnNetwork = objectMapper.convertValue(network, new TypeReference<OtnNetwork>() {
                });
            } else if (network.getNetworkId().equals(NetworkType.OpenroadmNetwork.getNetworkValue())) {
                roadMNetwork = objectMapper.convertValue(network, new TypeReference<RoadMNetwork>() {
                });
            } else if (network.getNetworkId().equals(NetworkType.OpenRoadmTopology.getNetworkValue())) {
                if (network.getLinks() != null) {
                    deviceNetwork = objectMapper.convertValue(network, new TypeReference<DeviceNetwork>() {
                    });
                }
            }
        }
        ParsedNetwork parsedNetwork = new ParsedNetwork(clliNetwork, roadMNetwork, deviceNetwork, otnNetwork);
        return parsedNetwork;
    }

    public ClliNetwork integrateNetworks(ParsedNetwork networks) {

        ClliNetwork clliNetwork = networks.getClliNetwork();
        RoadMNetwork roadMNetwork = networks.getRoadMNetwork();
        DeviceNetwork deviceNetwork = networks.getDeviceNetwork();
        OtnNetwork otnNetwork = networks.getOtnNetwork();

        if (deviceNetwork != null) {
            for (DeviceNode deviceNode : deviceNetwork.getNodes()) {
                deviceNode.getSupportingNode().forEach(supportingNode -> {
                    RoadMNode roadMNode = roadMNetwork.getNodeById(supportingNode.getNodeRef());
                    if (roadMNode != null) {
                        roadMNode.addDeviceNode(deviceNode);
                    }
                });
                // TODO raise exception or handle
            }
        }
        if (roadMNetwork != null) {
            for (RoadMNode roadMNode : roadMNetwork.getNodes()) {
                roadMNode.getSupportingNode().forEach(supportingNode -> {
                    ClliNode clliNode = clliNetwork.getNodeById(supportingNode.getNodeRef());
                    if (clliNode != null) {
                        clliNode.addRoadMNode(roadMNode);
                    }
                });
                // TODO raise exception or handle
            }
        }

        if (otnNetwork != null) {
            otnNetwork.getNodes().stream().forEach(otnNode -> {
                for (SupportingNode supportingNode : otnNode.getSupportingNode()) {
                    ClliNode node = clliNetwork.getNodeById(supportingNode.getNodeRef());
                    RoadMNode roadMNode = roadMNetwork.getNodeById(supportingNode.getNodeRef());
                    if (roadMNode != null) {
                        DeviceNode deviceNode1 = new DeviceNode();
                        deviceNode1.setNodeType(otnNode.getNodeType());
                        deviceNode1.setSupportingNode(otnNode.getSupportingNode());
                        deviceNode1.setNodeId(otnNode.getNodeId());
                        roadMNode.addDeviceNode(deviceNode1);
                    }
                   /* if (node != null) {
                        node.addRoadMNode(otnNode);
                    }*/
                }
            });
        }
        return clliNetwork;

    }
}
