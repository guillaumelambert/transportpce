package com.orange.onap.tpce.manager.feature.openroadm.visual.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.ParsedNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNode;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.Link;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.otn.OtnNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNode;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisualMapperService {

    private final String NODE_ID_SEPARATOR = "-";

    @Autowired
    private ObjectMapper objectMapper;

    List<VowlClass> classes;

    List<VowlClassAttribute> classAttributes;


    Map<String, VowlProperty> linksProperty;
    Map<String, VowlPropertyAttribute> linksAttributes;

    List<VowlProperty> vowlPropertiesList;
    List<VowlPropertyAttribute> vowlPropertyAttributeList;


    public WebVowl mapToWebVowl(ClliNetwork clliNetwork, ParsedNetwork parsedNetwork) {
        classes = new ArrayList<>();
        classAttributes = new ArrayList<>();

        vowlPropertiesList = new ArrayList<>();
        vowlPropertyAttributeList = new ArrayList<>();


        for (ClliNode clliNode : clliNetwork.getNodes()) {
            VowlClass vowlClass = new VowlClass();
            VowlClassAttribute vowlClassAttribute = new VowlClassAttribute();
            vowlClass.setId(clliNode.getNodeId());

            String label = clliNode.getNodeId();//extractLabel(clliNode.getNodeId(), 0);
            vowlClassAttribute.setId(clliNode.getNodeId());
            vowlClassAttribute.setLabel(label);
            vowlClassAttribute.setNodeType(clliNode.getNodeType());

            vowlClassAttribute.setChildren(parseRoadM(clliNode.getRoadMNodeMap()));

            classes.add(vowlClass);
            classAttributes.add(vowlClassAttribute);
        }

        this.mapVowlLinks(parsedNetwork);
        this.removeInternalLinkDegToSrg(parsedNetwork.getDeviceNetwork());

        for (String linkId : linksProperty.keySet()) {
            vowlPropertiesList.add(linksProperty.get(linkId));
            vowlPropertyAttributeList.add(linksAttributes.get(linkId));
        }
        return buildWebVowl();
    }

    private void removeInternalLinkDegToSrg(DeviceNetwork deviceNetwork) {

        List<String> idsToRemove = new ArrayList<>();

        for (String linkId : linksProperty.keySet()) {

            String linkFrom = linksAttributes.get(linkId).getDomain();
            String linkTo = linksAttributes.get(linkId).getRange();

            DeviceNode deviceNodeFrom = deviceNetwork.getNode(linkFrom);
            DeviceNode deviceNodeTo = deviceNetwork.getNode(linkTo);

            if (deviceNodeFrom != null && deviceNodeTo != null && deviceNodeFrom.getNodeRef().equalsIgnoreCase(deviceNodeTo.getNodeRef())) {

                if (deviceNodeFrom.getNodeType() != null && deviceNodeTo.getNodeType() != null) {
                    if (deviceNodeFrom.getNodeType().equalsIgnoreCase(DeviceNode.DEG_TYPE)
                            && deviceNodeTo.getNodeType().equalsIgnoreCase(DeviceNode.SRG_TYPE) ||
                            (deviceNodeFrom.getNodeType().equalsIgnoreCase(DeviceNode.SRG_TYPE) &&
                                    deviceNodeTo.getNodeType().equalsIgnoreCase(DeviceNode.DEG_TYPE))) {
                        idsToRemove.add(linkId);
                    } else {
                        clearLinkLabelWithSameNodeRef(linkId);
                    }
                }
                // TODO handle no type
            }
        }
        for (String linkId : idsToRemove) {
            linksProperty.remove(linkId);
            linksAttributes.remove(linkId);
        }
    }

    private void clearLinkLabelWithSameNodeRef(String linkId) {
        VowlPropertyAttribute linkAttribute = linksAttributes.get(linkId);
        // remove label for edges with the same ROADM
        linkAttribute.setLabel(" ");
    }

    public WebVowl buildWebVowl() {
        return new WebVowl(classes, classAttributes, vowlPropertiesList, vowlPropertyAttributeList);
    }

    private void mapVowlLinks(ParsedNetwork parsedNetwork) {

        DeviceNetwork deviceNetwork = parsedNetwork.getDeviceNetwork();
        OtnNetwork otnNetwork = parsedNetwork.getOtnNetwork();

        linksAttributes = new HashMap<>();
        linksProperty = new HashMap<>();

        if (deviceNetwork != null) {
            for (Link link : deviceNetwork.getLinks()) {

                VowlProperty vowlProperty = new VowlProperty();
                fillVowlProperty(link, vowlProperty);
            }
        }

        if (otnNetwork != null) {
            if (otnNetwork.getLinks() != null) {
                otnNetwork.getLinks().stream()
                        .filter(link -> !link.getLinkId().startsWith("ODU4"))
                        .forEach(link -> {
                            VowlProperty vowlProperty = new VowlProperty();
                            fillVowlProperty(link, vowlProperty);
                        });
            }
        }
    }

    private void fillVowlProperty(Link link, VowlProperty vowlProperty) {
        vowlProperty.setId(link.getLinkId());
        // System.out.println(link.getLinkId() +" -- "+link.getLinkType());
        vowlProperty.setLinkType(link.getLinkType());
        //owl:inverseOf
        //owl:ObjectProperty
        VowlPropertyAttribute vowlPropertyAttribute = new VowlPropertyAttribute();
        vowlPropertyAttribute.setId(link.getLinkId());
        vowlPropertyAttribute.setRange(link.getDestinationNode());
        vowlPropertyAttribute.setDomain(link.getSourceNode());
        vowlPropertyAttribute.setLabel(link.getLinkId());


        if (link.getOppositeLinkId() != null) {
            vowlPropertyAttribute.setInverse(link.getOppositeLinkId());
        }

        linksAttributes.put(link.getLinkId(), vowlPropertyAttribute);
        linksProperty.put(link.getLinkId(), vowlProperty);
    }

    private String extractLabel(String nodeId, String separator) {
        String[] label = nodeId.split(separator);
        return label[label.length - 1];
    }

    private List<VowlClassAttribute> parseRoadM(Map<String, RoadMNode> roadMNodeMap) {
        List<VowlClassAttribute> attributes = new ArrayList<>();

        for (RoadMNode roadMNode : roadMNodeMap.values()) {

            VowlClassAttribute vowlClassAttribute = new VowlClassAttribute();

            String nodeId = roadMNode.getNodeId();//extractLabel(roadMNode.getNodeId(), 0);
            vowlClassAttribute.setId(nodeId);
            vowlClassAttribute.setLabel(nodeId);
            vowlClassAttribute.setNodeType(roadMNode.getNodeType());
            vowlClassAttribute.setChildren(parseDevice(roadMNode.getDeviceNodeMap()));

            xpdrTagParentId(roadMNode.getNodeType(), vowlClassAttribute);
            xpdrSingleChildReplaceParent(roadMNode.getNodeType(), vowlClassAttribute);

            attributes.add(vowlClassAttribute);
        }
        return attributes;
    }

    private void xpdrTagParentId(String nodeType, VowlClassAttribute vowlClassAttribute) {
        if (nodeType != null && DeviceNode.XPDR_TYPE.toLowerCase().equals(nodeType.toLowerCase())) {
            vowlClassAttribute.setId(vowlClassAttribute.getId() + "#parent");
        }
    }

    private void xpdrSingleChildReplaceParent(String nodeType, VowlClassAttribute vowlClassAttribute) {
        if (nodeType != null && DeviceNode.XPDR_TYPE.toLowerCase().equals(nodeType.toLowerCase()) && vowlClassAttribute.getChildren().size() == 1) {
            VowlClassAttribute child = vowlClassAttribute.getChildren().get(0);
            vowlClassAttribute.setLabel(child.getId());
            vowlClassAttribute.setId(child.getId());
            vowlClassAttribute.setChildren(new ArrayList<>());
        }
    }

    private List<VowlClassAttribute> parseDevice(Map<String, DeviceNode> deviceNodeMap) {
        List<VowlClassAttribute> attributes = new ArrayList<>();

        for (DeviceNode deviceNode : deviceNodeMap.values()) {

            VowlClassAttribute vowlClassAttribute = new VowlClassAttribute();

            String label = extractLabel(deviceNode.getNodeId(), NODE_ID_SEPARATOR);
            vowlClassAttribute.setId(deviceNode.getNodeId());
            vowlClassAttribute.setNodeType(deviceNode.getNodeType());
            vowlClassAttribute.setLabel(label);

//            vowlClassAttribute.setTerminationPoints(topologyNode.getTerminationPoints());
//            vowlClassAttribute.setWavelengths(topologyNode.getAttributesWavelength());

            attributes.add(vowlClassAttribute);

        }
        return attributes;
    }
}
