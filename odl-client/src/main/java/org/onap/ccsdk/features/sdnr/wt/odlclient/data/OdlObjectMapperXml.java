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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.CustomDateAndTimeSerializer;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.CustomOdlDeserializer;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class OdlObjectMapperXml extends XmlMapper {

    private static final Logger LOG = LoggerFactory.getLogger(OdlObjectMapperXml.class);
    private static final long serialVersionUID = 1L;
    private static final String NORMALIZE_REGEX = "<[\\/]{0,1}([a-z]+[A-Z]+[^>]*)>";
    private static final Pattern NORMALIZE_PATTERN = Pattern.compile(NORMALIZE_REGEX, Pattern.MULTILINE);
    private final boolean doNormalize;
    private final YangToolsBuilderAnnotationIntrospector introspector;

    public OdlObjectMapperXml() {
        this(false);
    }

    public OdlObjectMapperXml(boolean doNormalize) {
        super();
        this.doNormalize = doNormalize;
        Bundle bundle = FrameworkUtil.getBundle(OdlObjectMapperXml.class);
        BundleContext context = bundle != null ? bundle.getBundleContext() : null;
        this.introspector = new YangToolsBuilderAnnotationIntrospector(context);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        enable(MapperFeature.USE_GETTERS_AS_SETTERS);
        setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        //setPropertyNamingStrategy(new YangToolsNamingStrategy());
        setSerializationInclusion(Include.NON_NULL);
        setAnnotationIntrospector(this.introspector);
        SimpleModule customSerializerModule = new SimpleModule();
        customSerializerModule.addSerializer(DateAndTime.class, new CustomDateAndTimeSerializer());
        //        customSerializerModule.addSerializer(ChoiceIn.class, new CustomChoiceSerializer());
        customSerializerModule.setDeserializerModifier(new CustomOdlDeserializer());

        this.registerModule(customSerializerModule);
        this.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }


    @Override
    public <T> T readValue(String content, Class<T> valueType) throws JsonMappingException, JsonProcessingException {
        if (this.doNormalize) {
            content = this.normalizeContent(content);
        }
        return super.readValue(content, valueType);
    }

    private String normalizeContent(String content) {
        LOG.debug("normalize content");
        LOG.trace("before={}", content);
        final Matcher matcher = NORMALIZE_PATTERN.matcher(content);
        String copy = content;
        String attr;
        KebabCaseStrategy converter = new KebabCaseStrategy();
        while (matcher.find()) {
            if (matcher.groupCount() > 0) {
                attr = matcher.group(1);
                copy = copy.replaceFirst(attr, converter.translate(attr));
            }
        }
        LOG.trace("after={}", copy);
        return copy;
    }

    public Class<?> findClass(String name, Class<?> clazz) throws ClassNotFoundException {
        return this.introspector.findClass(name, clazz);
    }


}
