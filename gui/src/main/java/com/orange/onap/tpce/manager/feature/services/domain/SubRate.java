package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubRate {
    @JsonProperty("committed-burst-size")
    private String committedBurstSize;
    
    @JsonProperty("committed-info-rate")
    private String committedInfoRate;

}
