/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OdlRpcObjectMapperXml extends OdlObjectMapperXml {

    private static final Logger LOG = LoggerFactory.getLogger(OdlRpcObjectMapperXml.class);
    private static final long serialVersionUID = 1L;
    private final OdlXmlSerializer serializer;

    public OdlRpcObjectMapperXml() {
        super();
        this.serializer = new OdlXmlSerializer();
    }

    @Override
    public String writeValueAsString(Object value) {
        return this.serializer.writeValueAsString(value, "input");
    }

}
