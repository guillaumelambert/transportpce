package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.SupportingNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNode;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadMNode {

    @JsonProperty("node-id")
    private String nodeId;

    @JsonProperty("supporting-node")
    private List<SupportingNode> supportingNode;

    @JsonProperty("org-openroadm-common-network:node-type")
    private String nodeType = "ROADM";
    private String vendor;
    private String ip;
    private String model;

    @JsonIgnore
    private Map<String, DeviceNode> deviceNodeMap;

    public RoadMNode() {
        this.deviceNodeMap = new HashMap<String, DeviceNode>();
    }

    public void addDeviceNode(DeviceNode deviceNode) {
        this.deviceNodeMap.put(deviceNode.getNodeId(), deviceNode);
    }


}
