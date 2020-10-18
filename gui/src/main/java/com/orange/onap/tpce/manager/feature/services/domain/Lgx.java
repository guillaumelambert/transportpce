package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lgx {
    @JsonProperty("lgx-port-name")
    private String lgxPortName;
    @JsonProperty("lgx-device-name")
    private String lgxDeviceName;
    @JsonProperty("lgx-port-shelf")
    private String lgxPortShelf;
    @JsonProperty("lgx-port-rack")
    private String lgxPortRack;
}
