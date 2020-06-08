/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.CustomDateAndTimeSerializer;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.CustomOdlDeserializer;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.YangToolsBuilderAnnotationIntrospector;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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

        this.registerModule(customSerializerModule);
        this.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }
}
