package com.orange.onap.tpce.manager.feature.openroadm.visual.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebVowl {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    // TODO Header


    @JsonProperty
    private Map<String, Object> header;
    @JsonProperty
    private final String _comment = " ";
    private List<VowlClass> vowlClassList;
    private List<VowlClassAttribute> classAttributeList;
    private List<VowlProperty> propertyList;
    private List<VowlPropertyAttribute> propertyAttributeList;

    public WebVowl(List<VowlClass> classes, List<VowlClassAttribute> classAttributes) {
        header = new HashMap<>();
        Map<String, String> label = new HashMap<>();
        label.put("undefined", "ONAP");
        header.put("title", label);

    }

    public WebVowl(List<VowlClass> classes, List<VowlClassAttribute> classAttributes, List<VowlProperty> vowlPropertiesList, List<VowlPropertyAttribute> vowlPropertyAttributeList) {
        super();
        this.vowlClassList = classes;
        this.classAttributeList = classAttributes;
        this.propertyList = vowlPropertiesList;
        this.propertyAttributeList = vowlPropertyAttributeList;
    }

    @JsonSetter("RoadM.nodes")
    public void setVowlClassList(List<?> nodes) {

        this.vowlClassList = objectMapper.convertValue(nodes, new TypeReference<List<VowlClass>>() {
        });
        this.classAttributeList = objectMapper.convertValue(nodes, new TypeReference<List<VowlClassAttribute>>() {
        });
    }

    @JsonGetter("class")
    public List<VowlClass> getVowlClassList() {
        return this.vowlClassList;
    }

    @JsonGetter("classAttribute")
    public List<VowlClassAttribute> getClassAttributeList() {
        return this.classAttributeList;
    }


    @JsonSetter("RoadM.links")
    public void setLink(List<?> links) {

        this.propertyList = objectMapper.convertValue(links, new TypeReference<List<VowlProperty>>() {
        });
        this.propertyAttributeList = objectMapper.convertValue(links, new TypeReference<List<VowlPropertyAttribute>>() {
        });
    }

    @JsonGetter("propertyAttribute")
    public List<VowlPropertyAttribute> getPropertyAttributeList() {
        return this.propertyAttributeList;
    }

    @JsonGetter("property")
    public List<VowlProperty> getPropertyList() {
        return this.propertyList;
    }


}
