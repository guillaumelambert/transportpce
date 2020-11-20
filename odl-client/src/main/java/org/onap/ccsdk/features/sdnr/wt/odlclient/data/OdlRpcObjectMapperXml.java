/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.serializer.KeepPropertyNameSerializer;

public class OdlRpcObjectMapperXml extends OdlObjectMapperXml {

    private static final long serialVersionUID = 1L;
    private final OdlXmlSerializer serializer;

    public OdlRpcObjectMapperXml() {
        super(true);
        this.serializer = new OdlXmlSerializer(this);
        this.serializer.setNullValueExcluded(true);
        this.serializer.addSerializer(org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.OpticalControlMode.class, new KeepPropertyNameSerializer());
        this.serializer.addSerializer("org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.packs.CircuitPacksBuilder$CircuitPacksImpl","_subSlot",new KeepPropertyNameSerializer());
        this.serializer.addSerializer("org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.RoadmConnectionsBuilder$RoadmConnectionsImpl","_opticalControlMode",new KeepPropertyNameSerializer());
//        this.serializer.addSerializer(org.opendaylight.yang.gen.v1.http.org.openroadm.equipment.states.types.rev171215.States.class, new KeepPropertyNameSerializer());
    }

    @Override
    public String writeValueAsString(Object value) {
    	return this.serializer.writeValueAsString(value, "input");
    }

    public String writeValueAsString(Object value, String rootName) {
    	return this.serializer.writeValueAsString(value, rootName);
    }

    public String writeValueAsString(Object data, Class<?> clazz) {
        KebabCaseStrategy converter = new KebabCaseStrategy();
        String clsName = clazz.getSimpleName();
        if(clsName.endsWith("Impl")) {
            clsName = clsName.substring(0, clsName.length()-4);
        }
       return this.writeValueAsString(data, converter.translate(clsName));
    }


}
