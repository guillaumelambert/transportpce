/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.TypeObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OdlXmlSerializer {

    private static final Logger LOG = LoggerFactory.getLogger(OdlXmlSerializer.class);

    private boolean nullValueExcluded;

    public OdlXmlSerializer() {

    }

    public void setNullValueExcluded(boolean exclude) {
        this.nullValueExcluded = exclude;
    }

    public boolean isExcludeNullValue() {
        return this.nullValueExcluded;
    }

    public String writeValueAsString(Object value, String rootKey) {

        StringBuilder sb = new StringBuilder();
        this.startElem(sb, rootKey);
        this.writeRecurseProperties(sb, value, 0);
        this.stopElem(sb, rootKey);
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private void writeRecurseProperties(StringBuilder sb, Object object, int level) {
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
                    if (name.startsWith("_")) {
                        if (this.nullValueExcluded && value == null) {
                            continue;
                        }
                        Class<?> type = field.getType();
                        //convert property name to kebab-case (yang-spec writing)
                        name = name.substring(1).replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase();
                        //if has inner childs
                        if (DataObject.class.isAssignableFrom(type)) {
                            this.startElem(sb, name);
                            this.writeRecurseProperties(sb, value, level + 1);
                            this.stopElem(sb, name);
                        } else {
                            //if enum
                            if (Enum.class.isAssignableFrom(type)) {
                                this.startElem(sb, name);
                                String svalue = String.valueOf(value);
                                this.writeElemValue(sb, svalue.substring(0, 1).toLowerCase() + svalue.substring(1));
                                this.stopElem(sb, name);
                            }
                            // type object (new type of base type) => use getValue()
                            else if (TypeObject.class.isAssignableFrom(type)) {
                                this.startElem(sb, name);
                                this.writeElemValue(sb, this.getTypeObjectStringValue(value, type));
                                this.stopElem(sb, name);
                            }
                            //if choice then jump over field and step into next java level, but not in xml
                            else if (ChoiceIn.class.isAssignableFrom(type)) {
                                this.writeRecurseProperties(sb, value, level);
                            }
                            //if list of elems
                            else if (value!=null && List.class.isAssignableFrom(type)) {
                                for (Object listObject : (List<Object>) value) {
                                    this.startElem(sb, name);
                                    this.writeRecurseProperties(sb, listObject, level + 1);
                                    this.stopElem(sb, name);
                                }
                            }
                            //by exclude all others it is basic value element
                            else {
                                this.startElem(sb, name);
                                this.writeElemValue(sb, value);
                                this.stopElem(sb, name);
                            }
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    LOG.warn("problem accessing value during mapping: ", e);
                }
            }
        }
    }

    private void startElem(StringBuilder sb, String elem) {
        sb.append(String.format("<%s>", elem));
    }

    private void writeElemValue(StringBuilder sb, Object elemValue) {
        sb.append(String.valueOf(elemValue));
    }

    private void stopElem(StringBuilder sb, String elem) {
        sb.append(String.format("</%s>", elem));
    }

    private String getTypeObjectStringValue(Object value, Class<?> type) {
        try {
            Method method = type.getMethod("getValue");
            return String.valueOf(method.invoke(value));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            LOG.warn("problem calling getValue fn: ", e);
        }
        return "";
    }
}
