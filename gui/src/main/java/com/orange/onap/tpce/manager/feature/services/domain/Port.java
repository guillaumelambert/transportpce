package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Port {

    @JsonProperty("port-type")
    private String portType;
    @JsonProperty("port-rack")
    private String portRack;
    @JsonProperty("port-name")
    private String portName;
    @JsonProperty("port-device-name")
    private String deviceName;
    @JsonProperty("port-shelf")
    private String portShelf;

}
