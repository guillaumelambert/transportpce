package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {

    @JsonProperty("link-id")
    private String linkId;
    @JsonProperty("org-openroadm-common-network:link-type")
    @JsonAlias("org-openroadm-otn-network-topology:link-type")
    private String linkType;
    @JsonProperty("source")
    private LinkPoint source;
    @JsonProperty("destination")
    private LinkPoint destination;
    @JsonProperty("org-openroadm-common-network:opposite-link")
    private String oppositeLinkId;

    private void setLinkType(String type) {
        this.linkType = type;
    }

    public String getSourceNode() {
        return this.source.getNodeId();
    }

    public String getDestinationNode() {
        return this.destination.getNodeId();
    }

}
