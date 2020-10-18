package com.orange.onap.tpce.manager.feature.services.domain.service.path;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;


@Getter
public class Resource {

    @JsonSetter("tp-node-id")
    private String tpNodeId;
    @JsonSetter("tp-id")
    private String tpId;

}
