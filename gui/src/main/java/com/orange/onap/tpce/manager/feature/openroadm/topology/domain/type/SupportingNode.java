package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SupportingNode {

    @JsonProperty("network-ref")
    private String networkRef;

    @JsonProperty("node-ref")
    private String nodeRef;
}
