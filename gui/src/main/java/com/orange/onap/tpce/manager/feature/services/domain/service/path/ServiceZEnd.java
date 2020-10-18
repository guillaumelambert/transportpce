package com.orange.onap.tpce.manager.feature.services.domain.service.path;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

@Getter
public class ServiceZEnd {

    @JsonSetter("service-format")
    private String serviceFormat;

    @JsonSetter("service-rate")
    private String serviceRate;

    @JsonSetter("clli")
    private String clli;

}
