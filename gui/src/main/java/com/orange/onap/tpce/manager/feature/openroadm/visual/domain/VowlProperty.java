package com.orange.onap.tpce.manager.feature.openroadm.visual.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VowlProperty {


    public static final String TYPE_DEFAULT_VALUE = "owl:ObjectProperty";

    private String id;
    private String type = TYPE_DEFAULT_VALUE;
    private String linkType;

    @JsonSetter("link-id")
    public void setId(String linkId) {
        this.id = linkId;
    }

    @JsonGetter("id")
    public String getId() {
        return this.id;
    }

    @JsonSetter(value = "link-type")
    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @JsonGetter("type")
    public String getType() {
        return this.type;
    }

    @JsonGetter("linkType")
    public String getLinkType() {
        return this.linkType;
    }

}
