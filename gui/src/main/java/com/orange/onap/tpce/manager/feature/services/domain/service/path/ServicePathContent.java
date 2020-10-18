package com.orange.onap.tpce.manager.feature.services.domain.service.path;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

import java.util.List;

@Getter
public class ServicePathContent {

    @JsonSetter("service-paths")
    private List<ServicePath> servicePathList;

}
