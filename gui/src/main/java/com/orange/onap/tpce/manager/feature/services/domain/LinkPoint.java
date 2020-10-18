package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkPoint {

    @JsonProperty("node-id")
    private String nodeId;

    @JsonProperty("service-format")
    private String serviceFormat;

    @JsonProperty("service-rate")
    private String serviceRate;

    @JsonProperty("tx-direction")
    private TxDirection txDirection;

    @JsonProperty("rx-direction")
    private RxDirection rxDirection;

    @JsonProperty("subrate-eth-sla")
    private SubRateContainer subRateContainer;

    @JsonProperty("optic-type")
    private String opticType;

    @JsonProperty("clli")
    private String clli;


    public String getNodeId() {
        return txDirection.getPort().getDeviceName();
    }
}
