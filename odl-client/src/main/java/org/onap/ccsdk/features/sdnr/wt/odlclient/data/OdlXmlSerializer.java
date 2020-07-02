/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import java.lang.reflect.Field;
import org.opendaylight.yangtools.yang.common.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OdlXmlSerializer extends OdlDataSerializer<StringBuilder>{

    private static final Logger LOG = LoggerFactory.getLogger(OdlXmlSerializer.class);

    public OdlXmlSerializer() {
        super();
    }

    @Override
    void preValueWrite(StringBuilder sb, String key,Object o) {
        sb.append(String.format("<%s %s>", key, this.getXmlNameSpace(o)));

    }

    @Override
    void postValueWrite(StringBuilder sb, String key) {
        sb.append(String.format("</%s>",key));

    }

    @Override
    void onValueWrite(StringBuilder sb, Object o) {
        sb.append(String.valueOf(o));

    }

    @Override
    StringBuilder instantiateBuilder() {
       return new StringBuilder();
    }

    private String getXmlNameSpace(Object o) {
        Field f;
        Object couldQName=null;
        try {

            f = o.getClass().getField("QNAME");
            couldQName = f.get(o);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException  e) {
           //no need to handle this
        }
        if (couldQName instanceof QName) {
            QName qname = (QName) couldQName;
            return  "xmlns=\""+qname.getNamespace().toString()+"\"" ;
        }
        return "";
    }
}
