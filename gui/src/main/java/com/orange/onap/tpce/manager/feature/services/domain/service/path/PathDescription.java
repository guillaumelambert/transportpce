package com.orange.onap.tpce.manager.feature.services.domain.service.path;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

@Getter
public class PathDescription {

    @JsonSetter("aToZ-direction")
    private AToZDirection aToZDirection;

}
