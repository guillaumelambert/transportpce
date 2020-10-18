package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Service {

    @JsonProperty("service-name")
    private String serviceName;

    @JsonSetter("service-z-end")
    private LinkPoint sourcePoint;

    @JsonSetter("service-a-end")
    private LinkPoint targetPoint;

    @JsonProperty("sdnc-request-header")
    private SdncRequestHeader sdncRequestHeader;

    @JsonProperty("administrative-state")
    private String administractiveState;

    @JsonProperty("connection-type")
    private String connectionType;

    @JsonProperty("is-bandwidth-locked")
    private boolean bandwidthLocked;

    @JsonProperty("service-layer")
    private String serviceLayer;

    @JsonProperty("lifecycle-state")
    private String lifecycleState;

    @JsonProperty("common-id")
    private String commonId;

    @JsonProperty("operational-state")
    private String operationalState;




   /* @JsonGetter("source")
    public LinkPoint getSourcePoint() {
        return this.sourcePoint;
    }

    @JsonGetter("target")
    public LinkPoint getTargetPoint() {
        return this.targetPoint;
    }*/
}
