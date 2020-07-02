/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OdlJsonSerializer extends OdlDataSerializer<JSONObject>{

    private static final Logger LOG = LoggerFactory.getLogger(OdlJsonSerializer.class);

    public OdlJsonSerializer() {
        super();
    }

    @Override
    JSONObject instantiateBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    void preValueWrite(JSONObject builder, String key, Object value) {
        // TODO Auto-generated method stub

    }

    @Override
    void postValueWrite(JSONObject builder, String key) {
        // TODO Auto-generated method stub

    }

    @Override
    void onValueWrite(JSONObject builder, Object o) {
        // TODO Auto-generated method stub

    }
}
