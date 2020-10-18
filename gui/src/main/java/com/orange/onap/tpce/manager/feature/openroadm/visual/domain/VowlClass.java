package com.orange.onap.tpce.manager.feature.openroadm.visual.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VowlClass {

    private String id;

    @JsonSetter("node-id")
    public void setId(String nodeId) {
        this.id = nodeId;
    }

    @JsonGetter("id")
    public String getId() {
        return this.id;
    }

    @JsonProperty(value = "type")
    private final String type = "owl:RectangleClass";

//   @JsonGetter("type")
//   public String getType(){
//      return this.type;
//   }
}
