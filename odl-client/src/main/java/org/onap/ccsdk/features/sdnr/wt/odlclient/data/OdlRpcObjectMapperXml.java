/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import org.opendaylight.netconf.util.NetconfUtil;
import org.opendaylight.yangtools.util.xml.UntrustedXML;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.xml.XMLStreamNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.model.api.SchemaContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class OdlRpcObjectMapperXml extends OdlObjectMapperXml {

    private static final Logger LOG = LoggerFactory.getLogger(OdlRpcObjectMapperXml.class);
    private static final long serialVersionUID = 1L;
    private final SchemaContext schemaContext;
    private final NormalizedNodeStreamWriter sw;
    final StringWriter writer = new StringWriter();
    final OdlObjectMapperXml simpleMapper;

    public OdlRpcObjectMapperXml(SchemaContext context) throws XMLStreamException {
        super();
        this.schemaContext = context;
        this.simpleMapper = new OdlObjectMapperXml();
        final DOMResult domResult = new DOMResult(UntrustedXML.newDocumentBuilder().newDocument());
        XMLStreamWriter writern = XMLOutputFactory.newDefaultFactory().createXMLStreamWriter(this.writer);
        this.sw = XMLStreamNormalizedNodeStreamWriter.create(writern, context);
    }

    @Override
    public String writeValueAsString(Object value) throws JsonProcessingException {
        // return this.writer().withRootName("input").writeValueAsString(value);

        final NormalizedNodeWriter normalizedNodeWriter = NormalizedNodeWriter.forStreamWriter(this.sw);
        NormalizedNode<?, ?> node = (NormalizedNode<?, ?>) value;
        try {
           // Document doc = loadXmlDocument(simpleMapper.writeValueAsString(value));
//            Document doc = loadXmlDocument("<input>\n" +
//                    "    <enabled>true</enabled>\n" +
//                    "    <equipment-entity>\n" +
//                    "        <shelf-name>1/0</shelf-name>\n" +
//                    "    </equipment-entity>\n" +
//                    "</input>");
            //final DOMSource domSource = new DOMSource(doc.getDocumentElement());
            //node = NetconfUtil.transformDOMSourceToNormalizedNode(this.schemaContext, domSource).getResult();
            normalizedNodeWriter.write(node);
            normalizedNodeWriter.flush();
//        } catch (XMLStreamException | URISyntaxException | IOException | SAXException e) {
//            LOG.error("a", e);
//        } catch (ParserConfigurationException e) {
//            LOG.error("b", e);
//        }
        }catch(IOException e) {
            LOG.error("c",e);
        }

        final String serializedXml = writer.toString();
        return serializedXml;
    }

    private Document loadXmlDocument(String content) throws SAXException, IOException, ParserConfigurationException {
        LOG.debug("parsing {} to xml doc", content);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream((content).getBytes()));

        return doc;
    }
}
