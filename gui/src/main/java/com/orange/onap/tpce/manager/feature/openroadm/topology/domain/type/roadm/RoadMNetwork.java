package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.Network;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadMNetwork extends Network<RoadMNode> {

    private Map<String, RoadMNode> nodeMap;
//    public RoadMNetwork(Network network){
//        this.links = network.getLinks();
//        this.nodes = network.getNodes();
//        this.networkId = network.getNetworkId();
//        setNodeMap(this.nodes);
//    }

    @JsonSetter("node-list")
    private void setNodeMap(List<RoadMNode> nodeList) {
        nodeMap = new HashMap<>();
        for (RoadMNode node : nodeList) {
            nodeMap.put(node.getNodeId(), node);
        }
    }

    public List<RoadMNode> getNodes() {
        return new ArrayList<>(this.nodeMap.values());
    }

    public RoadMNode getNodeById(String nodeId) {
        return this.nodeMap.get(nodeId);
    }
}
