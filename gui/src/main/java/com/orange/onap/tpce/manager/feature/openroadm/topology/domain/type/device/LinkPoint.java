package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkPoint {

    @JsonAlias({"source-node", "dest-node"})
    private String nodeId;

    @JsonAlias({"source-tp", "dest-tp"})
    private String tpId;

    public String getNodeId() {
        return this.nodeId;
    }

}
