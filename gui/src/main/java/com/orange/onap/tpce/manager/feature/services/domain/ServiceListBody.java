package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceListBody {

    @JsonProperty("service-list")
    private ServiceList serviceList;
}
