package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubRateContainer {

    @JsonProperty("subrate-eth-sla")
    private SubRate subRate;

}
