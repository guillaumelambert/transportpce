package com.orange.onap.tpce.manager.feature.services.domain.service.path;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

import java.util.List;

@Getter
public class AToZDirection {
    private String rate;
    @JsonSetter("aToZ")
    private List<Node> nodeList;

}
