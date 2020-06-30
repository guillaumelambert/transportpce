/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;


import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OdlRpcObjectMapperXml2 {

    private static final Logger LOG = LoggerFactory.getLogger(OdlRpcObjectMapperXml2.class);

    public OdlRpcObjectMapperXml2() {

    }

    public String writeValueAsString(Object value) throws ParserConfigurationException, TransformerException {
        return this.writeValueAsString(value, "input");
    }

    public String writeValueAsString(Object value, String rootKey)
            throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().newDocument();
        Element root = doc.createElement(rootKey);
        doc.appendChild(root);
        this.writeRecurseProperties(doc, root, value, 0);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        return output;
    }

    private void writeRecurseProperties(Document doc, Element root, Object object, int level) {
        if (level > 15) {
            System.out.println("Level to deep protection.");
        } else {
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                try {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(object);
                    //only _xxx properties are interesting
                    if (value != null && name.startsWith("_")) {
                        Class<?> type = field.getType();
                        //convert property name to kebab-case (yang-spec writing)
                        name = name.substring(1).replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase();
                        //if has inner childs
                        if (DataObject.class.isAssignableFrom(type)) {
                            Element xobj = doc.createElement(name);
                            root.appendChild(xobj);
                            this.writeRecurseProperties(doc, xobj, value, level + 1);
                        } else {
                            //if is basic value element => write element to root node
                            if (isValueElementType(type)) {
                                Element xobj = doc.createElement(name);
                                xobj.setTextContent(String.valueOf(value));
                                root.appendChild(xobj);

                            }
                            //if choice then jump over field and step into next java level, but not in xml
                            else if (ChoiceIn.class.isAssignableFrom(type)) {
                                this.writeRecurseProperties(doc, root, value, level);
                            }
                            else if (List.class.isAssignableFrom(type)) {
                                for (Object listObject : (List<Object>) value) {
                                    Element xobj = doc.createElement(name);
                                    root.appendChild(xobj);
                                    this.writeRecurseProperties(doc, xobj,listObject, level + 1);
                                }
                            }

                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private boolean isValueElementType(Class<?> type) {
        if (Boolean.class.isAssignableFrom(type)) {
            return true;
        }
        if (String.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }
}
