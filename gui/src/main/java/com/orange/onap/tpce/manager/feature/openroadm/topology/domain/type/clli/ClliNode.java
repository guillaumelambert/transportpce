package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClliNode {

    @JsonProperty("node-id")
    private String nodeId;
    private String clli;

    @JsonProperty("org-openroadm-common-network:node-type")
    private String nodeType = "CLLI";

    @JsonIgnore
    private Map<String, RoadMNode> roadMNodeMap;

    public ClliNode() {
        this.roadMNodeMap = new HashMap<String, RoadMNode>();
    }

    public void addRoadMNode(RoadMNode roadMNode) {
        this.roadMNodeMap.put(roadMNode.getNodeId(), roadMNode);
    }

}
