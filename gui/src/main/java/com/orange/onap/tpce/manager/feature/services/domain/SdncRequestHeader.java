package com.orange.onap.tpce.manager.feature.services.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Sdnc request header.
 */
@Setter
@Getter
public class SdncRequestHeader {

    @JsonProperty("request-id")
    private String requestId;

    @JsonProperty("rpc-action")
    private String rpcAction;

    @JsonProperty("request-system-id")
    private String requestSystemId;

}
