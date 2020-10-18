package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute {

    @JsonProperty(value = "degree-number")
    private Integer degreeNumber;

    @JsonProperty("available-wavelengths")
    private List<Map<String, Integer>> waveLengths;
}
