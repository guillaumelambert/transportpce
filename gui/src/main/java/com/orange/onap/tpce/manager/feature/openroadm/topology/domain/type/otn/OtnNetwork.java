package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.otn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.Network;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.SupportingNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shaaban Ebrahim
 */
@Getter
@Setter
public class OtnNetwork extends Network<RoadMNode> {

    private Map<String, RoadMNode> nodeMap;


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

