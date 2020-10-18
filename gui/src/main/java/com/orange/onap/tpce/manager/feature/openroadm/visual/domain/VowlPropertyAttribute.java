package com.orange.onap.tpce.manager.feature.openroadm.visual.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VowlPropertyAttribute {

    public static final String LABEL_DEFAULT_KEY = "undefined";

    private String id;
    private Map<String, String> label;
    private String range;
    private String domain;
    private String inverse;

    //    @JsonSetter(value = "label")
//    public void setLabel(String label){
    public VowlPropertyAttribute() {
        if (this.label == null) {
            this.label = new HashMap<>();
        }
        this.label.put(LABEL_DEFAULT_KEY, " ");
    }

    public void setLabel(String label) {
        this.label.put(LABEL_DEFAULT_KEY, label);
    }
//    @JsonSetter("link-id")
//    public void setId(String linkId){
//        this.id  = linkId;
//    }
//
//    @JsonGetter("id")
//    public String getId(){
//        return this.id;
//    }
//
//
//    @JsonSetter("source.nodeId")
//    private void setDomain(String destinationNodeId) {
//
//        this.domain = destinationNodeId;
//    }

//    @JsonGetter("domain")
//    public String getDomain(){
//        return this.domain;
//    }
//
//    @JsonSetter("destination.nodeId")
//    private void setRange(String sourceNodeId) {
//
//        this.range = sourceNodeId;
//    }

//    @JsonGetter("range")
//    public String getRange(){
//        return this.range;
//    }
}
