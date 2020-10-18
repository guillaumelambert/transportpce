package com.orange.onap.tpce.manager.feature.openroadm.visual.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.TerminationPoint;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VowlClassAttribute {

    public static final String LABEL_DEFAULT_KEY = "undefined";
    private static final String WAVELENGTH_DEFAULT_KEY = "index";

    private String id;
    //    @JsonProperty(value = "label")
    private Map<String, String> label;

    private String nodeType;

    private List<String> wavelengths;

    private List<Map<String, String>> terminationPoints;

    private List<VowlClassAttribute> children;

//    public void setIdAndLabel(String nodeId){
//        this.id = nodeId;
//        if(label == null){
//            label = new HashMap<>();
//        }
//        label.put(LABEL_DEFAULT_KEY, nodeId);
//    }

    public void setLabel(String nodeId) {
        if (label == null) {
            label = new HashMap<>();
        }
        label.put(LABEL_DEFAULT_KEY, nodeId);
    }

    @JsonGetter("id")
    public String getId() {
        return this.id;
    }

    public void setWavelengths(List<Map<String, Integer>> wavelengthsList) {

        this.wavelengths = new ArrayList<>();
        for (Map<String, Integer> wavelength : wavelengthsList) {
            this.wavelengths.add(wavelength.get(WAVELENGTH_DEFAULT_KEY).toString());
        }
    }

    @JsonGetter("wavelengths")
    public List<String> getWavelengths() {
        return this.wavelengths;
    }

    public void setTerminationPoints(List<TerminationPoint> terminationPointList) {
        this.terminationPoints = new ArrayList<>();
        for (TerminationPoint terminationPoint : terminationPointList) {
            Map<String, String> idTypeMap = new HashMap<>();
            idTypeMap.put(TerminationPoint.TP_ID, terminationPoint.getTpId());
            idTypeMap.put(TerminationPoint.TP_TYPE, terminationPoint.getTpType());
            this.terminationPoints.add(idTypeMap);
        }
    }

    @JsonGetter("terminationPoints")
    public List<Map<String, String>> getTerminationPoints() {
        return this.terminationPoints;
    }

}
