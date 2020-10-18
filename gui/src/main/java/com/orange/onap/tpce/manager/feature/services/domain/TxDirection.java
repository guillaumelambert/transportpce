package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxDirection {
    @JsonProperty("port")
    private Port port;

    @JsonProperty("lgx")
    private Lgx lgx;

}
