package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli;

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
public class ClliNetwork extends Network<ClliNode> {

    private Map<String, ClliNode> nodeMap;

//    public ClliNetwork(Network network){
//        this.nodes = network.getNodes();
//        this.networkId = network.getNetworkId();
//        setNodeMap(this.nodes);
//    }

    @JsonSetter("node-list")
    private void setNodeMap(List<ClliNode> nodeList) {
        this.nodeMap = new HashMap<>();
        for (ClliNode node : nodeList) {
            nodeMap.put(node.getNodeId(), node);
        }
    }

    public ClliNode getNodeById(String nodeId) {
        return this.nodeMap.get(nodeId);
    }

    public List<ClliNode> getNodes() {
        return new ArrayList<>(this.nodeMap.values());
    }

}
