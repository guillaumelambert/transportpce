package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.Link;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Network<T> {

    @JsonProperty("network-id")
    private String networkId;

    @JsonProperty("network-types")
    private Map<String, Object> networkType;

    @JsonProperty("node")
    private Object nodes;

    @JsonSetter("node")
    private void setNodes(Object nodeList) {
        this.nodes = nodeList;
    }

    @JsonGetter("node-list")
    public Object getNodes() {
        return this.nodes;
    }

    @JsonProperty("ietf-network-topology:link")
    private List<Link> links;
}
