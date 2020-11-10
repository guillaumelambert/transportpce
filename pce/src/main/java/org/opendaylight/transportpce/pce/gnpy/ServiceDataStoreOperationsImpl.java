/*
 * Copyright © 2018 Orange, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.transportpce.pce.gnpy;

import com.google.common.collect.FluentIterable;
<<<<<<< HEAD
import java.io.BufferedWriter;
=======
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
>>>>>>> standalone/stable/aluminium
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
<<<<<<< HEAD
import java.util.Collections;
import java.util.Optional;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingNormalizedNodeCodecRegistry;
import org.opendaylight.mdsal.binding.generator.impl.ModuleInfoBackedContext;
import org.opendaylight.mdsal.binding.generator.util.BindingRuntimeContext;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.transportpce.common.DataStoreContext;
import org.opendaylight.transportpce.common.converter.XMLDataObjectConverter;
import org.opendaylight.transportpce.common.network.NetworkTransactionService;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev170206.org.openroadm.device.container.OrgOpenroadmDevice;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.YangModuleInfo;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
=======
import org.opendaylight.mdsal.binding.dom.codec.spi.BindingDOMCodecServices;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yang.gen.v1.gnpy.gnpy.api.rev190103.GnpyApi;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> standalone/stable/aluminium

public class ServiceDataStoreOperationsImpl implements ServiceDataStoreOperations {

    private static final JsonParser PARSER = new JsonParser();
    private BindingDOMCodecServices bindingDOMCodecServices;

<<<<<<< HEAD
    public ServiceDataStoreOperationsImpl(NetworkTransactionService networkTransactionService) {
    }

    @Override
    public void createXMLFromDevice(DataStoreContext dataStoreContextUtil, OrgOpenroadmDevice device, String output)
        throws GnpyException {

        if (device != null) {
            Optional<NormalizedNode<?, ?>> transformIntoNormalizedNode = null;
            XMLDataObjectConverter cwDsU = XMLDataObjectConverter.createWithDataStoreUtil(dataStoreContextUtil);
            transformIntoNormalizedNode = cwDsU.toNormalizedNodes(device, OrgOpenroadmDevice.class);
            if (!transformIntoNormalizedNode.isPresent()) {
                throw new GnpyException(String.format(
                    "In ServiceDataStoreOperationsImpl: Cannot transform the device %s into normalized nodes",
                    device.toString()));
            }
            Writer writerFromDataObject =
                cwDsU.writerFromDataObject(device, OrgOpenroadmDevice.class,cwDsU.dataContainer());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output,StandardCharsets.UTF_8))) {
                writer.write(writerFromDataObject.toString());
            } catch (IOException e) {
                throw new GnpyException(
                    String.format("In ServiceDataStoreOperationsImpl : Bufferwriter error %s",e));
            }
            LOG.debug("GNPy: device xml : {}", writerFromDataObject);
        }
    }

    @Override
    public String createJsonStringFromDataObject(final InstanceIdentifier<?> id, DataObject object)
        throws GnpyException, Exception {

        final SchemaPath scPath = SchemaPath.create(FluentIterable
                .from(id.getPathArguments())
                .transform(input -> BindingReflections.findQName(input.getType())), true);

        // Prepare the variables
        final ModuleInfoBackedContext moduleContext = ModuleInfoBackedContext.create();
        Iterable<? extends YangModuleInfo> moduleInfos = Collections
                .singleton(BindingReflections.getModuleInfo(object.getClass()));
        moduleContext.addModuleInfos(moduleInfos);
        SchemaContext schemaContext = moduleContext.tryToCreateSchemaContext().get();
        BindingRuntimeContext bindingContext;
        bindingContext = BindingRuntimeContext.create(moduleContext, schemaContext);
        final BindingNormalizedNodeCodecRegistry codecRegistry =
            new BindingNormalizedNodeCodecRegistry(bindingContext);

        /*
         * This function needs : - context - scPath.getParent() -
         * scPath.getLastComponent().getNamespace(), -
         * JsonWriterFactory.createJsonWriter(writer)
         */
        final Writer writer = new StringWriter();

        try (NormalizedNodeStreamWriter domWriter = JSONNormalizedNodeStreamWriter.createExclusiveWriter(
                JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02.createSimple(schemaContext),
                scPath.getParent(), scPath.getLastComponent().getNamespace(),
                JsonWriterFactory.createJsonWriter(writer, 2));) {
            // The write part
            codecRegistry.getSerializer(id.getTargetType()).serialize(object, codecRegistry.newWriter(id, domWriter));
        } catch (IOException e) {
            throw new GnpyException("In ServiceDataStoreOperationsImpl: exception during json file creation",e);
=======
    public ServiceDataStoreOperationsImpl(BindingDOMCodecServices bindingDOMCodecServices) throws GnpyException {
        this.bindingDOMCodecServices = bindingDOMCodecServices;
    }

    @Override
    public String createJsonStringFromDataObject(final InstanceIdentifier<GnpyApi> id, GnpyApi object)
        throws GnpyException {
        final SchemaPath scPath = SchemaPath.create(FluentIterable
                .from(id.getPathArguments())
                .transform(input -> BindingReflections.findQName(input.getType())), true);
        /*
         * This function needs : - context - scPath.getParent() -
         * scPath.getLastComponent().getNamespace(), -
         * JsonWriterFactory.createJsonWriter(writer)
         */

        JSONCodecFactory codecFactory = JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02
                .getShared(bindingDOMCodecServices.getRuntimeContext().getEffectiveModelContext());
        try (Writer writer = new StringWriter();
                JsonWriter jsonWriter = JsonWriterFactory.createJsonWriter(writer, 2);) {
            NormalizedNodeStreamWriter jsonStreamWriter = JSONNormalizedNodeStreamWriter.createExclusiveWriter(
                    codecFactory, scPath.getParent(), scPath.getLastComponent().getNamespace(), jsonWriter);
            try (NormalizedNodeWriter nodeWriter = NormalizedNodeWriter.forStreamWriter(jsonStreamWriter)) {
                nodeWriter.write(bindingDOMCodecServices.toNormalizedNode(id, object).getValue());
                nodeWriter.flush();
            }
            JsonObject asJsonObject = PARSER.parse(writer.toString()).getAsJsonObject();
            return new Gson().toJson(asJsonObject);
        } catch (IOException e) {
            throw new GnpyException("Cannot convert data to Json string", e);
>>>>>>> standalone/stable/aluminium
        }
    }

    // Write the json as a string in a file
    @Override
    public void writeStringFile(String jsonString, String fileName) throws GnpyException {
        try (FileWriter file = new FileWriter(fileName,StandardCharsets.UTF_8)) {
            file.write(jsonString);
        } catch (IOException e) {
            throw new GnpyException("In ServiceDataStoreOperationsImpl : exception during file writing",e);
        }
    }
}
