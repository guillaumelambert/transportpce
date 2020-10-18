package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TerminationPoint {

    public static final String TP_ID = "tp-id";
    public static final String TP_TYPE = "org-openroadm-common-network:tp-type";

    @JsonProperty(TP_ID)
    private String tpId;

    @JsonProperty(TP_TYPE)
    private String tpType;
}
