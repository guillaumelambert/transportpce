package com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.SupportingNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceNode {


    public static final String XPDR_TYPE = "XPONDER";
    public static final String SRG_TYPE = "SRG";
    public static final String DEG_TYPE = "DEGREE";

    @JsonProperty("node-id")
    private String nodeId;

    @JsonProperty("supporting-node")
    private List<SupportingNode> supportingNode;

    @JsonProperty("ietf-network-topology:termination-point")
    private List<TerminationPoint> terminationPoints;
    @JsonProperty("org-openroadm-common-network:node-type")
    private String nodeType = "DEVICE";
    private Attribute attributes;

    @JsonAnySetter
    private void setAttributes(String key, Object obj) {
        //key.startsWith("org-openroadm-network-RoadM:") &&
        if (key.endsWith("-attributes")) {
            ObjectMapper mapper = new ObjectMapper();

            attributes = mapper.convertValue(obj, Attribute.class);//(Map<String, List<Map<String,?>>>)obj;
        }
    }

    public List<Map<String, Integer>> getAttributesWavelength() {
        if (this.attributes != null)
            return this.attributes.getWaveLengths();
        return new ArrayList<>();
    }

    public String getNodeRef() {
        if (this.supportingNode != null && this.supportingNode.get(0).getNodeRef() != null)
            return this.supportingNode.get(0).getNodeRef();
        return "";
    }

}
