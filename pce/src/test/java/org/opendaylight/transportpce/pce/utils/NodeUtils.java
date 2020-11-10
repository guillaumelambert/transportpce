/*
 * Copyright © 2020 Orange, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.transportpce.pce.utils;

<<<<<<< HEAD
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
=======
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.transportpce.common.NetworkUtils;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.link.types.rev181130.FiberPmd;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.link.types.rev181130.RatioDB;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130.Link1Builder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.state.types.rev181130.State;
<<<<<<< HEAD
import org.opendaylight.yang.gen.v1.http.org.openroadm.degree.rev181130.degree.node.attributes.AvailableWavelengthsBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.equipment.states.types.rev181130.AdminStates;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.AmplifiedLink;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.amplified.link.SectionElementBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.span.attributes.LinkConcatenation;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.span.attributes.LinkConcatenationBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Link1;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.TerminationPoint1;
=======
import org.opendaylight.yang.gen.v1.http.org.openroadm.degree.rev181130.degree.node.attributes.AvailableWavelengths;
import org.opendaylight.yang.gen.v1.http.org.openroadm.degree.rev181130.degree.node.attributes.AvailableWavelengthsBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.equipment.states.types.rev181130.AdminStates;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.AmplifiedLink;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.AmplifiedLinkKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.amplified.link.SectionElementBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.span.attributes.LinkConcatenation;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.span.attributes.LinkConcatenationBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.span.attributes.LinkConcatenationKey;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.TerminationPoint1Builder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.link.OMSAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.link.oms.attributes.AmplifiedLinkBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.link.oms.attributes.SpanBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.DegreeAttributes;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.DegreeAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.SrgAttributes;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.SrgAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.CpAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.CtpAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.PpAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.RxTtpAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.TxTtpAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.XpdrClientAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.XpdrNetworkAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.networks.network.node.termination.point.XpdrPortAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.types.rev181130.OpenroadmLinkType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.types.rev181130.OpenroadmTpType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.types.rev181130.xpdr.tp.supported.interfaces.SupportedInterfaceCapability;
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.types.rev181130.xpdr.tp.supported.interfaces.SupportedInterfaceCapabilityBuilder;
<<<<<<< HEAD
=======
import org.opendaylight.yang.gen.v1.http.org.openroadm.network.types.rev181130.xpdr.tp.supported.interfaces.SupportedInterfaceCapabilityKey;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.network.topology.rev181130.networks.network.node.termination.point.TpSupportedInterfaces;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.network.topology.rev181130.networks.network.node.termination.point.TpSupportedInterfacesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.network.topology.rev181130.networks.network.node.termination.point.XpdrTpPortConnectionAttributesBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.port.types.rev181130.If100GEODU4;
import org.opendaylight.yang.gen.v1.http.org.openroadm.port.types.rev181130.If10GEODU2e;
import org.opendaylight.yang.gen.v1.http.org.openroadm.port.types.rev181130.If1GEODU0;
import org.opendaylight.yang.gen.v1.http.org.openroadm.port.types.rev181130.IfOCHOTU4ODU4;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.NetworkId;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.NodeId;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.networks.network.NodeBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.networks.network.NodeKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.networks.network.node.SupportingNode;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.networks.network.node.SupportingNodeBuilder;
<<<<<<< HEAD
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.LinkId;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1;
=======
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev180226.networks.network.node.SupportingNodeKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.LinkId;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1Builder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.TpId;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.Link;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.LinkBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.LinkKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.link.DestinationBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.link.SourceBuilder;
<<<<<<< HEAD
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.node.TerminationPointBuilder;
import org.opendaylight.yangtools.yang.binding.Augmentation;
=======
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.node.TerminationPoint;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.networks.network.node.TerminationPointBuilder;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.common.Uint16;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.yangtools.yang.common.Uint32;

public class NodeUtils {

    private static final String LINK_ID_FORMAT = "%1$s-%2$sto%3$s-%4$s";

    public static LinkBuilder createLinkBuilder(
            String srcNode, String destNode, String srcTp, String destTp, Link1Builder link1Builder) {
        SourceBuilder ietfSrcLinkBldr =
                new SourceBuilder().setSourceNode(new NodeId(srcNode)).setSourceTp(srcTp);
        //create destination link
        DestinationBuilder ietfDestLinkBldr =
                new DestinationBuilder().setDestNode(new NodeId(destNode)).setDestTp(destTp);
        LinkId linkId = new LinkId(String.format(LINK_ID_FORMAT, srcNode, srcTp, destNode, destTp));

        LinkId oppositeLinkId = new LinkId("OpenROADM-3-2-DEG1-to-OpenROADM-3-1-DEG1");
        //For setting up attributes for openRoadm augment
        OMSAttributesBuilder omsAttributesBuilder =
                new OMSAttributesBuilder().setOppositeLink(oppositeLinkId);

        // Augementation
        Augmentation<Link> aug11 = new org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130
                .Link1Builder()
<<<<<<< HEAD
                .setAdministrativeGroup(Long.valueOf(123))
                .setAdministrativeState(State.InService)
                .setAmplified(true)
                .setLinkLatency(Long.valueOf(123))
=======
                .setAdministrativeGroup(Uint32.valueOf(123))
                .setAdministrativeState(State.InService)
                .setAmplified(true)
                .setLinkLatency(Uint32.valueOf(123))
>>>>>>> standalone/stable/aluminium
                .setLinkLength(BigDecimal.valueOf(123))
                .setOMSAttributes(new OMSAttributesBuilder()
                        .setOppositeLink(new LinkId("OpenROADM-3-2-DEG1-to-OpenROADM-3-1-DEG1"))
                        .setSpan(new SpanBuilder().build())
<<<<<<< HEAD
                        .setTEMetric(Long.valueOf(123)).build())
=======
                        .setTEMetric(Uint32.valueOf(123)).build())
>>>>>>> standalone/stable/aluminium
                .setOperationalState(State.InService).build();

        LinkBuilder linkBuilder = new LinkBuilder()
                .setSource(ietfSrcLinkBldr.build())
                .setDestination(ietfDestLinkBldr.build())
                .setLinkId(linkId)
<<<<<<< HEAD
                .addAugmentation(Link1.class, aug11)
                .withKey(new LinkKey(linkId));

        linkBuilder.addAugmentation(
                org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130.Link1.class,
                link1Builder.build());
=======
                .addAugmentation(aug11)
                .withKey(new LinkKey(linkId));

        linkBuilder.addAugmentation(link1Builder.build());
>>>>>>> standalone/stable/aluminium
        return linkBuilder;
    }

    public static LinkBuilder createRoadmToRoadm(String srcNode, String destNode, String srcTp, String destTp) {
        Link1Builder link1Builder = new Link1Builder()
<<<<<<< HEAD
                .setLinkLatency(30L)
=======
                .setLinkLatency(Uint32.valueOf(30))
>>>>>>> standalone/stable/aluminium
                .setLinkType(OpenroadmLinkType.ROADMTOROADM);
        return createLinkBuilder(srcNode, destNode, srcTp, destTp, link1Builder);

    }

<<<<<<< HEAD
    public static List<SupportingNode> geSupportingNodes() {
        List<SupportingNode> supportingNodes1 = new ArrayList<>();
//
        supportingNodes1
                .add(new SupportingNodeBuilder()
                        .setNodeRef(new NodeId("node 1"))
                        .setNetworkRef(new NetworkId(NetworkUtils.CLLI_NETWORK_ID))
                        .build());

        supportingNodes1
                .add(new SupportingNodeBuilder()
                        .setNodeRef(new NodeId("node 2"))
                        .setNetworkRef(new NetworkId(NetworkUtils.UNDERLAY_NETWORK_ID))
                        .build());
        return supportingNodes1;
    }

    public static NodeBuilder getNodeBuilder(List<SupportingNode> supportingNodes1) {
=======
    public static Map<SupportingNodeKey, SupportingNode> geSupportingNodes() {
        Map<SupportingNodeKey, SupportingNode> supportingNodes1 = new HashMap<>();
        SupportingNode supportingNode1 = new SupportingNodeBuilder()
                .setNodeRef(new NodeId("node 1"))
                .setNetworkRef(new NetworkId(NetworkUtils.CLLI_NETWORK_ID))
                .build();
        supportingNodes1
                .put(supportingNode1.key(),supportingNode1);

        SupportingNode supportingNode2 = new SupportingNodeBuilder()
                .setNodeRef(new NodeId("node 2"))
                .setNetworkRef(new NetworkId(NetworkUtils.UNDERLAY_NETWORK_ID))
                .build();
        supportingNodes1
                .put(supportingNode2.key(),supportingNode2);
        return supportingNodes1;
    }

    public static NodeBuilder getNodeBuilder(Map<SupportingNodeKey,SupportingNode> supportingNodes1) {
>>>>>>> standalone/stable/aluminium


        //update tp of nodes
        TerminationPointBuilder xpdrTpBldr = new TerminationPointBuilder();
        TerminationPoint1Builder tp1Bldr = new TerminationPoint1Builder();

        tp1Bldr.setTpType(OpenroadmTpType.XPONDERNETWORK);
<<<<<<< HEAD
        xpdrTpBldr.addAugmentation(TerminationPoint1.class, tp1Bldr.build());
        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 node1 =
                new Node1Builder().setTerminationPoint(ImmutableList.of(xpdrTpBldr.build())).build();
=======
        xpdrTpBldr.addAugmentation(tp1Bldr.build());
        TerminationPoint xpdr = xpdrTpBldr.build();
        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 node1 =
                new Node1Builder().setTerminationPoint(Map.of(xpdr.key(),xpdr)).build();
>>>>>>> standalone/stable/aluminium


        return new NodeBuilder()
                .setNodeId(new NodeId("node 1"))
                .withKey(new NodeKey(new NodeId("node 1")))
<<<<<<< HEAD
                .addAugmentation(
                        Node1.class, node1)
=======
                .addAugmentation(node1)
>>>>>>> standalone/stable/aluminium
                .setSupportingNode(supportingNodes1);
    }

    private Link genereateLinkBuilder() {

<<<<<<< HEAD
        List<LinkConcatenation> linkConcentationValues = new ArrayList<>();
        LinkConcatenation linkConcatenation = new LinkConcatenationBuilder()
                .setFiberType(LinkConcatenation.FiberType.Truewave)
                .setPmd(new FiberPmd(BigDecimal.ONE))
                .setSRLGId(Long.valueOf(1))
                .setSRLGLength(Long.valueOf(1))
=======
        Map<LinkConcatenationKey,LinkConcatenation> linkConcentationValues = new HashMap<>();
        LinkConcatenation linkConcatenation = new LinkConcatenationBuilder()
                .setFiberType(LinkConcatenation.FiberType.Truewave)
                .setPmd(new FiberPmd(BigDecimal.ONE))
                .setSRLGId(Uint32.valueOf(1))
                .setSRLGLength(Uint32.valueOf(1))
>>>>>>> standalone/stable/aluminium
                .build();
        LinkConcatenation linkConcatenation2 = new LinkConcatenationBuilder()
                .setFiberType(LinkConcatenation.FiberType.Truewave)
                .setPmd(new FiberPmd(BigDecimal.ONE))
<<<<<<< HEAD
                .setSRLGId(Long.valueOf(1))
                .setSRLGLength(Long.valueOf(1))
                .build();
        linkConcentationValues.add(linkConcatenation);
        linkConcentationValues.add(linkConcatenation2);

        List<AmplifiedLink>
                amplifiedLinkValues = new ArrayList<>();
=======
                .setSRLGId(Uint32.valueOf(1))
                .setSRLGLength(Uint32.valueOf(1))
                .build();
        linkConcentationValues.put(linkConcatenation.key(),linkConcatenation);
        linkConcentationValues.put(linkConcatenation2.key(),linkConcatenation2);

        Map<AmplifiedLinkKey,AmplifiedLink>
                amplifiedLinkValues = new HashMap<>();
>>>>>>> standalone/stable/aluminium
        org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.AmplifiedLink al =
                new org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes
                        .AmplifiedLinkBuilder().setSectionElement(new SectionElementBuilder()
                        .setSectionElement(new org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130
                                .amplified.link.attributes.amplified.link.section.element.section.element
                                .SpanBuilder()
                                .setSpan(
                                        new org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified
                                                .link.attributes.amplified.link.section.element.section
                                                .element.span.SpanBuilder()
                                                .setAdministrativeState(AdminStates.InService)
                                                .setAutoSpanloss(true)
                                                .setClfi("clfi")
                                                .setEngineeredSpanloss(new RatioDB(BigDecimal.ONE))
                                                .setLinkConcatenation(linkConcentationValues)
                                                .setSpanlossBase(new RatioDB(BigDecimal.ONE))
                                                .setSpanlossCurrent(new RatioDB(BigDecimal.ONE))
                                                .build())
                                .build())
                        .build())
<<<<<<< HEAD
                        .setSectionEltNumber(Integer.valueOf(1)).build();
=======
                        .setSectionEltNumber(Uint16.valueOf(1)).build();
>>>>>>> standalone/stable/aluminium
        org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes.AmplifiedLink al2 =
                new org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link.attributes
                        .AmplifiedLinkBuilder().setSectionElement(new SectionElementBuilder()
                        .setSectionElement(
                                new org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130.amplified.link
                                        .attributes.amplified.link.section.element.section.element.SpanBuilder()
                                        .setSpan(
                                                new org.opendaylight.yang.gen.v1.http.org.openroadm.link.rev181130
                                                        .amplified.link
                                                        .attributes.amplified.link.section.element.section.element.span
                                                        .SpanBuilder()
                                                        .setAdministrativeState(AdminStates.InService)
                                                        .setAutoSpanloss(true)
                                                        .setClfi("clfi")
                                                        .setEngineeredSpanloss(new RatioDB(BigDecimal.ONE))
                                                        .setLinkConcatenation(linkConcentationValues)
                                                        .setSpanlossBase(new RatioDB(BigDecimal.ONE))
                                                        .setSpanlossCurrent(new RatioDB(BigDecimal.ONE))
                                                        .build())
                                        .build())
                        .build())
<<<<<<< HEAD
                        .setSectionEltNumber(Integer.valueOf(1)).build();

        amplifiedLinkValues.add(al);
        amplifiedLinkValues.add(al2);
        Augmentation<Link> aug11 =
                new org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Link1Builder()
                        .setAdministrativeGroup(Long.valueOf(123))
                        .setAdministrativeState(State.InService)
                        .setAmplified(true)
                        .setLinkLatency(Long.valueOf(123))
=======
                        .setSectionEltNumber(Uint16.valueOf(1)).build();

        amplifiedLinkValues.put(al.key(),al);
        amplifiedLinkValues.put(al2.key(),al2);
        Augmentation<Link> aug11 =
                new org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Link1Builder()
                        .setAdministrativeGroup(Uint32.valueOf(123))
                        .setAdministrativeState(State.InService)
                        .setAmplified(true)
                        .setLinkLatency(Uint32.valueOf(123))
>>>>>>> standalone/stable/aluminium
                        .setLinkLength(BigDecimal.valueOf(123))
                        .setOMSAttributes(new OMSAttributesBuilder()
                                .setAmplifiedLink(new AmplifiedLinkBuilder()
                                        .setAmplifiedLink(amplifiedLinkValues)
                                        .build())
                                .setOppositeLink(new LinkId("link 1"))
                                .setSpan(new SpanBuilder().build())
<<<<<<< HEAD
                                .setTEMetric(Long.valueOf(123)).build())
=======
                                .setTEMetric(Uint32.valueOf(123)).build())
>>>>>>> standalone/stable/aluminium
                        .setOperationalState(State.InService).build();

        TransactionUtils.getNetworkForSpanLoss();
        return new LinkBuilder()
                .setLinkId(new LinkId("OpenROADM-3-1-DEG1-to-OpenROADM-3-2-DEG1"))
                .setSource(
                        new SourceBuilder()
                                .setSourceNode(new NodeId("OpenROADM-3-2-DEG1"))
                                .setSourceTp("DEG1-TTP-TX").build())
                .setDestination(
                        new DestinationBuilder()
                                .setDestNode(new NodeId("OpenROADM-3-1-DEG1"))
                                .setDestTp("DEG1-TTP-RX").build())
<<<<<<< HEAD
                .addAugmentation(Link1.class, aug11)
=======
                .addAugmentation(aug11)
>>>>>>> standalone/stable/aluminium
                .build();


    }

    // OTN network node
    public static List<SupportingNode> getOTNSupportingNodes() {
        List<SupportingNode> supportingNodes1 = new ArrayList<>();
        supportingNodes1
                .add(new SupportingNodeBuilder()
                        .setNodeRef(new NodeId("node 1"))
                        .setNetworkRef(new NetworkId(NetworkUtils.CLLI_NETWORK_ID))
                        .build());

        supportingNodes1
                .add(new SupportingNodeBuilder()
                        .setNodeRef(new NodeId("node 2"))
                        .setNetworkRef(new NetworkId(NetworkUtils.UNDERLAY_NETWORK_ID))
                        .build());
        return supportingNodes1;
    }

<<<<<<< HEAD
    public static NodeBuilder getOTNNodeBuilder(List<SupportingNode> supportingNodes1,
=======
    public static NodeBuilder getOTNNodeBuilder(Map<SupportingNodeKey,SupportingNode> supportingNodes1,
>>>>>>> standalone/stable/aluminium
                                                OpenroadmTpType openroadmTpType) {

        org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130.TerminationPoint1Builder
                tp1Bldr = getTerminationPoint1Builder(openroadmTpType);
        TerminationPointBuilder xpdrTpBldr = getTerminationPointBuilder(openroadmTpType);
        xpdrTpBldr
<<<<<<< HEAD
                .addAugmentation(
                        org.opendaylight
                                .yang.gen.v1.http.org.openroadm.common.network.rev181130.TerminationPoint1.class,
                        tp1Bldr.build());

        xpdrTpBldr.addAugmentation(
                org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130
                        .TerminationPoint1.class,
                createAnother2TerminationPoint(openroadmTpType).build());
        xpdrTpBldr.addAugmentation(
                org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.TerminationPoint1.class,
                createAnotherTerminationPoint(openroadmTpType).build());

        org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1 node1 = getNode1();
        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 node1Rev180226 =
                new Node1Builder()
                        .setTerminationPoint(ImmutableList.of(xpdrTpBldr.build()))
=======
                .addAugmentation(tp1Bldr.build());

        xpdrTpBldr.addAugmentation(createAnother2TerminationPoint(openroadmTpType).build());
        xpdrTpBldr.addAugmentation(createAnotherTerminationPoint(openroadmTpType).build());

        org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1 node1 = getNode1();
        TerminationPoint xpdr = xpdrTpBldr.build();
        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 node1Rev180226 =
                new Node1Builder()
                        .setTerminationPoint(Map.of(xpdr.key(),xpdr))
>>>>>>> standalone/stable/aluminium
                        .build();


        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 nodeIetf =
                new org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226
                        .Node1Builder()
<<<<<<< HEAD
                        .setTerminationPoint(ImmutableList.of(xpdrTpBldr.build()))
=======
                        .setTerminationPoint(Map.of(xpdr.key(),xpdr))
>>>>>>> standalone/stable/aluminium
                        .build();

        return new NodeBuilder()
                .setNodeId(new NodeId("node_test"))
                .withKey(new NodeKey(new NodeId("node 1")))
<<<<<<< HEAD
                .addAugmentation(
                        Node1.class, node1Rev180226)
                .addAugmentation(
                        org.opendaylight
                                .yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1.class,
                        node1)
                .addAugmentation(
                        org.opendaylight
                                .yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1.class,
                        nodeIetf
                )
                .setSupportingNode(supportingNodes1);
    }

    public static NodeBuilder getOTNNodeBuilderEmpty(List<SupportingNode> supportingNodes1,
=======
                .addAugmentation(node1Rev180226)
                .addAugmentation(node1)
                .addAugmentation(nodeIetf)
                .setSupportingNode(supportingNodes1);
    }

    public static NodeBuilder getOTNNodeBuilderEmpty(Map<SupportingNodeKey,SupportingNode> supportingNodes1,
>>>>>>> standalone/stable/aluminium
                                                     OpenroadmTpType openroadmTpType) {

        org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130.TerminationPoint1Builder tp1Bldr =
                getTerminationPoint1Builder(openroadmTpType);
        TerminationPointBuilder xpdrTpBldr = getTerminationPointBuilder(openroadmTpType);
<<<<<<< HEAD
        xpdrTpBldr.addAugmentation(
                org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130
                        .TerminationPoint1.class,
                tp1Bldr.build());
        xpdrTpBldr.addAugmentation(
                org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130
                        .TerminationPoint1.class,
                createAnotherTerminationPoint(openroadmTpType).build());

        org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1 node1 = getNode1Empty();
        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 node1Rev180226 =
                new Node1Builder()
                        .setTerminationPoint(ImmutableList.of(xpdrTpBldr.build()))
=======
        xpdrTpBldr.addAugmentation(tp1Bldr.build());
        xpdrTpBldr.addAugmentation(createAnotherTerminationPoint(openroadmTpType).build());

        org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1 node1 = getNode1Empty();
        TerminationPoint xpdr = xpdrTpBldr.build();
        org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.topology.rev180226.Node1 node1Rev180226 =
                new Node1Builder()
                        .setTerminationPoint(Map.of(xpdr.key(),xpdr))
>>>>>>> standalone/stable/aluminium
                        .build();


        return new NodeBuilder()
                .setNodeId(new NodeId("node_test"))
                .withKey(new NodeKey(new NodeId("node 1")))
<<<<<<< HEAD
                .addAugmentation(
                        Node1.class, node1Rev180226)
                .addAugmentation(
                        org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1.class,
                        node1)
=======
                .addAugmentation(node1Rev180226)
                .addAugmentation(node1)
>>>>>>> standalone/stable/aluminium
                .setSupportingNode(supportingNodes1);
    }

    private static org.opendaylight
            .yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1 getNode1() {
        return new org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1Builder()
                .setSrgAttributes(getSrgAttributes())
                .setDegreeAttributes(getDegAttributes())
                .build();
    }

    private static org.opendaylight
            .yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1 getNode1Empty() {
        return new org.opendaylight.yang.gen.v1.http.org.openroadm.network.topology.rev181130.Node1Builder()
                .setSrgAttributes(getEmptySrgAttributes())
                .setDegreeAttributes(getEmptyDegAttributes())
                .build();
    }

    private static DegreeAttributes getDegAttributes() {
<<<<<<< HEAD
        return (new DegreeAttributesBuilder())
                .setAvailableWavelengths(
                        Collections.singletonList(new AvailableWavelengthsBuilder()
                                .setIndex(20L)
                                .build()))
=======
        AvailableWavelengths aval = new AvailableWavelengthsBuilder()
                .setIndex(Uint32.valueOf(20))
                .build();
        return (new DegreeAttributesBuilder())
                .setAvailableWavelengths(Map.of(aval.key(),aval))
>>>>>>> standalone/stable/aluminium
                .build();
    }

    private static SrgAttributes getSrgAttributes() {
        return new SrgAttributesBuilder().setAvailableWavelengths(create96AvalWaveSrg()).build();
    }

    private static DegreeAttributes getEmptyDegAttributes() {
        return (new DegreeAttributesBuilder())
<<<<<<< HEAD
                .setAvailableWavelengths(
                        new ArrayList<>())
=======
                .setAvailableWavelengths(Map.of())
>>>>>>> standalone/stable/aluminium
                .build();
    }

    private static SrgAttributes getEmptySrgAttributes() {
<<<<<<< HEAD
        List<org.opendaylight.yang.gen
                .v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengths>
                waveList = new ArrayList<>();
        return new SrgAttributesBuilder().setAvailableWavelengths(waveList).build();
=======
        return new SrgAttributesBuilder().setAvailableWavelengths(Map.of()).build();
>>>>>>> standalone/stable/aluminium
    }

    private static TerminationPointBuilder getTerminationPointBuilder(OpenroadmTpType openroadmTpType) {
        return new TerminationPointBuilder()
                .setTpId(new TpId("2"))
<<<<<<< HEAD
                .addAugmentation(
                        org.opendaylight.yang.gen.v1.http.org.openroadm.otn.network.topology.rev181130
                                .TerminationPoint1.class,
                        createOTNTerminationPoint(openroadmTpType).build());
=======
                .addAugmentation(createOTNTerminationPoint(openroadmTpType).build());
>>>>>>> standalone/stable/aluminium
    }

    private static org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130.TerminationPoint1Builder
        getTerminationPoint1Builder(OpenroadmTpType openroadmTpType) {

        return new org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130.TerminationPoint1Builder()
                .setTpType(openroadmTpType);

    }

    private static org.opendaylight.yang.gen
            .v1.http.org.openroadm.network.topology.rev181130.TerminationPoint1Builder createAnotherTerminationPoint(
            OpenroadmTpType openroadmTpType
    ) {
        return new org.opendaylight
                .yang.gen.v1.http.org.openroadm.network.topology.rev181130.TerminationPoint1Builder()
                .setTpType(openroadmTpType)
<<<<<<< HEAD
                .setCtpAttributes((new CtpAttributesBuilder()).setUsedWavelengths(new ArrayList<>()).build())
                .setCpAttributes((new CpAttributesBuilder()).setUsedWavelengths(new ArrayList<>()).build())
                .setTxTtpAttributes((new TxTtpAttributesBuilder()).setUsedWavelengths(new ArrayList<>()).build())
                .setRxTtpAttributes((new RxTtpAttributesBuilder()).setUsedWavelengths(new ArrayList<>()).build())
                .setPpAttributes((new PpAttributesBuilder()).setUsedWavelength(new ArrayList<>()).build())
=======
                .setCtpAttributes((new CtpAttributesBuilder()).setUsedWavelengths(Map.of()).build())
                .setCpAttributes((new CpAttributesBuilder()).setUsedWavelengths(Map.of()).build())
                .setTxTtpAttributes((new TxTtpAttributesBuilder()).setUsedWavelengths(Map.of()).build())
                .setRxTtpAttributes((new RxTtpAttributesBuilder()).setUsedWavelengths(Map.of()).build())
                .setPpAttributes((new PpAttributesBuilder()).setUsedWavelength(Map.of()).build())
>>>>>>> standalone/stable/aluminium
                .setXpdrClientAttributes((new XpdrClientAttributesBuilder()).build())
                .setXpdrPortAttributes((new XpdrPortAttributesBuilder()).build())
                .setXpdrNetworkAttributes(new XpdrNetworkAttributesBuilder()
                        .setTailEquipmentId("destNode" + "--" + "destTp").build());
    }

    private static org.opendaylight.yang.gen.v1.http.org.openroadm.otn.network.topology.rev181130
            .TerminationPoint1Builder createOTNTerminationPoint(OpenroadmTpType openroadmTpType) {

        SupportedInterfaceCapability supIfCapa = new SupportedInterfaceCapabilityBuilder()
                .setIfCapType(IfOCHOTU4ODU4.class)
                .build();

        SupportedInterfaceCapability supIfCapa1 = new SupportedInterfaceCapabilityBuilder()
                .setIfCapType(If100GEODU4.class)
                .build();
        SupportedInterfaceCapability supIfCapa2 = new SupportedInterfaceCapabilityBuilder()
                .setIfCapType(If10GEODU2e.class)
                .build();
        SupportedInterfaceCapability supIfCapa3 = new SupportedInterfaceCapabilityBuilder()
                .setIfCapType(If1GEODU0.class)
                .build();

<<<<<<< HEAD
        List<SupportedInterfaceCapability> supIfCapaList = new ArrayList<>();
        supIfCapaList.add(supIfCapa);
        supIfCapaList.add(supIfCapa1);
        supIfCapaList.add(supIfCapa2);
        supIfCapaList.add(supIfCapa3);
=======
        Map<SupportedInterfaceCapabilityKey,SupportedInterfaceCapability> supIfCapaList = new HashMap<>();
        supIfCapaList.put(supIfCapa.key(),supIfCapa);
        supIfCapaList.put(supIfCapa1.key(),supIfCapa1);
        supIfCapaList.put(supIfCapa2.key(),supIfCapa2);
        supIfCapaList.put(supIfCapa3.key(),supIfCapa3);
>>>>>>> standalone/stable/aluminium

        TpSupportedInterfaces tpSupIf = new TpSupportedInterfacesBuilder()
                .setSupportedInterfaceCapability(supIfCapaList)
                .build();

        XpdrTpPortConnectionAttributesBuilder xtpcaBldr = new XpdrTpPortConnectionAttributesBuilder();

        return new org.opendaylight.yang.gen.v1.http.org.openroadm.otn.network.topology.rev181130
                .TerminationPoint1Builder()
                .setTpType(openroadmTpType)
                .setTpSupportedInterfaces(tpSupIf)
                .setXpdrTpPortConnectionAttributes(xtpcaBldr.build());
    }

    private static org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130
            .TerminationPoint1Builder createAnother2TerminationPoint(OpenroadmTpType openroadmTpType) {
        return new org.opendaylight.yang.gen.v1.http.org.openroadm.common.network.rev181130
                .TerminationPoint1Builder()
                .setTpType(openroadmTpType);
    }

<<<<<<< HEAD
    private static List<org.opendaylight.yang.gen
            .v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengths> create96AvalWaveSrg() {

        List<org.opendaylight.yang.gen.v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengths>
                waveList = new ArrayList<>();
=======
    private static Map<org.opendaylight.yang.gen
        .v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengthsKey,
        org.opendaylight.yang.gen
            .v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengths> create96AvalWaveSrg() {

        Map<org.opendaylight.yang.gen.v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengthsKey,
            org.opendaylight.yang.gen.v1.http.org.openroadm.srg.rev181130.srg.node.attributes.AvailableWavelengths>
                waveList = new HashMap<>();
>>>>>>> standalone/stable/aluminium

        for (int i = 1; i < 97; i++) {
            org.opendaylight.yang.gen.v1.http.org.openroadm.srg.rev181130.srg.node.attributes
                    .AvailableWavelengthsBuilder avalBldr
                = new org.opendaylight.yang.gen.v1.http.org.openroadm.srg.rev181130.srg.node.attributes
                    .AvailableWavelengthsBuilder()
                        .setIndex(Uint32.valueOf(i))
                        .withKey(new org.opendaylight.yang.gen.v1.http.org.openroadm.srg.rev181130.srg.node.attributes
                            .AvailableWavelengthsKey(Uint32.valueOf(i)));
<<<<<<< HEAD
            waveList.add(avalBldr.build());
=======
            org.opendaylight.yang.gen.v1.http.org.openroadm.srg
                .rev181130.srg.node.attributes.AvailableWavelengths aval =
                    avalBldr.build();
            waveList.put(aval.key(),aval);
>>>>>>> standalone/stable/aluminium
        }
        return waveList;
    }

}
