/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.CustomDateAndTimeSerializer;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.CustomOdlDeserializer;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.YangToolsBuilderAnnotationIntrospector;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Host;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.IpAddress;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNodeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNodeConnectionStatus.ConnectionStatus;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.connection.status.AvailableCapabilities;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.connection.status.AvailableCapabilitiesBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.connection.status.available.capabilities.AvailableCapability;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.connection.status.available.capabilities.AvailableCapability.CapabilityOrigin;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.connection.status.available.capabilities.AvailableCapabilityBuilder;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class OdlObjectMapperXml extends XmlMapper {

    private static final Logger LOG = LoggerFactory.getLogger(OdlObjectMapperXml.class);
    private static final long serialVersionUID = 1L;

    public OdlObjectMapperXml() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        enable(MapperFeature.USE_GETTERS_AS_SETTERS);
        setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        setSerializationInclusion(Include.NON_NULL);
        setAnnotationIntrospector(new YangToolsBuilderAnnotationIntrospector());
        SimpleModule customSerializerModule = new SimpleModule();
        customSerializerModule.addSerializer(DateAndTime.class, new CustomDateAndTimeSerializer());
        //        customSerializerModule.addSerializer(ChoiceIn.class, new CustomChoiceSerializer());
        customSerializerModule.setDeserializerModifier(new CustomOdlDeserializer());
        customSerializerModule.addKeyDeserializer(DataObject.class, new KeyDeserializer() {

            @Override
            public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
                if (key.contains(":")) {
                    String[] hlp = key.split(":");
                    key = hlp[hlp.length - 1];
                }
                LOG.trace("using key= {}", key);
                return ctxt.getAttribute(key);
            }
        });
        customSerializerModule.setSerializerModifier(new BeanSerializerModifier() {
            @Override
            public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc,
                    JsonSerializer<?> serializer) {
                LOG.info("a {}", beanDesc.getBeanClass());
                return super.modifySerializer(config, beanDesc, serializer);
            }

            @Override
            public BeanSerializerBuilder updateBuilder(SerializationConfig config, BeanDescription beanDesc,
                    BeanSerializerBuilder builder) {
                LOG.info("b {}", beanDesc.getBeanClass());
                return super.updateBuilder(config, beanDesc, builder);
            }

            @Override
            public JsonSerializer<?> modifyKeySerializer(SerializationConfig config, JavaType valueType,
                    BeanDescription beanDesc, JsonSerializer<?> serializer) {
                LOG.info("c {}", beanDesc.getBeanClass());
                return super.modifyKeySerializer(config, valueType, beanDesc, serializer);
            }

        });
        this.registerModule(customSerializerModule);
        this.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    /**
     * Get Builder object for yang tools interface.
     *
     * @param <T> yang-tools base datatype
     * @param clazz class with interface.
     * @return builder for interface or null if not existing
     */
    @SuppressWarnings("unchecked")
    private <T extends DataObject> Builder<T> getBuilder(Class<T> clazz) {
        String builder = clazz.getName() + "Builder";
        try {
            Class<?> clazzBuilder = Class.forName(builder);
            return (Builder<T>) clazzBuilder.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOG.debug("Problem ", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readValue(String content, Class<T> valueType)
            throws IOException, JsonParseException, JsonMappingException {
        LOG.debug("read value for class {}", valueType.getName());
        if (NetconfNode.class.isAssignableFrom(valueType)) {
            try {
                return (T) this.mapNetconfNodeXml(content);
            } catch (ParserConfigurationException | SAXException | XPathExpressionException e) {
                LOG.warn("problem parsing netconfnode: ", e);
            }
        }

        return super.readValue(content, valueType);

    }

    private NetconfNode mapNetconfNodeXml(String xmlContent)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
        Boolean reconnectOnChangedSchema =
                Boolean.valueOf(getElemValue(doc, "/node/reconnect-on-changed-schema", "false"));
        BigDecimal sleepFactor = BigDecimal.valueOf(Double.parseDouble(getElemValue(doc, "/node/sleep-factor", "0")));
        Host host = new Host(new IpAddress(Ipv4Address.getDefaultInstance(getElemValue(doc, "/node/host", null))));
        ConnectionStatus connectionStatus =
                ConnectionStatus.forName(getElemValue(doc, "/node/connection-status", null)).get();
        Uint16 betweenAttemptsTimeoutMillis =
                Uint16.valueOf(getElemValue(doc, "/node/between-attempts-timeout-millis", "120"));

        AvailableCapabilities capabilities =
                new AvailableCapabilitiesBuilder().setAvailableCapability(this.getCapsList(doc)).build();
        return new NetconfNodeBuilder().setReconnectOnChangedSchema(reconnectOnChangedSchema)
                .setSleepFactor(sleepFactor).setHost(host).setBetweenAttemptsTimeoutMillis(betweenAttemptsTimeoutMillis)
                .setConnectionStatus(connectionStatus).setAvailableCapabilities(capabilities).build();
    }


    private List<AvailableCapability> getCapsList(Document doc) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xPath.evaluate("/node/available-capabilities/available-capability", doc,
                XPathConstants.NODESET);
        if (nodes == null || nodes.getLength() <= 0) {
            return null;
        }
        List<AvailableCapability> caps = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            AvailableCapabilityBuilder builder;

            String origin = getElemValue(node, "./capability-origin", null);
            builder = new AvailableCapabilityBuilder().setCapability(getElemValue(node, "./capability", ""));
            if (origin != null)
                builder.setCapabilityOrigin(CapabilityOrigin.forName(origin).get());

            caps.add(builder.build());

        }
        return caps;
    }



    private String getElemValue(Object doc, String key, String def) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.evaluate(key, doc, XPathConstants.NODE);
        if (node != null) {
            def = node.getTextContent();
        }
        return def;
    }

}
