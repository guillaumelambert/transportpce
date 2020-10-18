package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceNetwork extends Network<DeviceNode> {

    private Map<String, DeviceNode> nodeMap;

//    @JsonProperty("network-id")
//    private String networkId;

//    @JsonProperty("link")
//    private List<Link> links;

//    public TopologyNetwork(Network network){
//        this.links = network.getLinks();
//        this.nodes = network.getNodes();
//        this.networkId = network.getNetworkId();
//    }

    @JsonSetter("node-list")
    private void setNodeMap(List<DeviceNode> nodeList) {
        nodeMap = new HashMap<>();
        for (DeviceNode node : nodeList) {
            nodeMap.put(node.getNodeId(), node);
        }
    }

    public List<DeviceNode> getNodes() {
        return new ArrayList<>(this.nodeMap.values());
    }

    public DeviceNode getNode(String nodeId) {
        return nodeMap.get(nodeId);
    }
}
