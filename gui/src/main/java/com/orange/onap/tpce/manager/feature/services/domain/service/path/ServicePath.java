package com.orange.onap.tpce.manager.feature.services.domain.service.path;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

@Getter
public class ServicePath {

    @JsonSetter("service-path-name")
    private String servicePathName;
    @JsonSetter("path-description")
    private PathDescription pathDescription;
    @JsonSetter("service-z-end")
    private ServiceZEnd serviceZEnd;

}
