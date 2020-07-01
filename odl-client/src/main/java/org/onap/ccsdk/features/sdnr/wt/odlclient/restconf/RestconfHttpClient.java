/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.restconf;

import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.odlclient.config.RemoteOdlConfig.AuthMethod;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlRpcObjectMapperXml;
import org.onap.ccsdk.features.sdnr.wt.odlclient.http.BaseHTTPClient;
import org.onap.ccsdk.features.sdnr.wt.odlclient.http.BaseHTTPResponse;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Uri;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Identifier;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.RpcError.ErrorType;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestconfHttpClient extends BaseHTTPClient {

    private static final Logger LOG = LoggerFactory.getLogger(RestconfHttpClient.class);
    private static final int DEFAULT_TIMEOUT = 5000;
    private final Map<String, String> headers;
    private final OdlRpcObjectMapperXml mapper;

    public RestconfHttpClient(String base, boolean trustAllCerts, AuthMethod authMethod, String username,
            String password) throws Exception {
        super(base, trustAllCerts);
        if (authMethod == AuthMethod.TOKEN) {
            throw new Exception("not yet implemented");
        }
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", "application/xml");
        this.headers.put("Authorization",
                BaseHTTPClient.getAuthorizationHeaderValue(username, password));
        this.headers.put("Accept", "application/xml");
        this.mapper = new OdlRpcObjectMapperXml();

    }

    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> read(LogicalDatastoreType storage,
            InstanceIdentifier<T> instanceIdentifier, String nodeId)
            throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        final String uri = this.getRfc8040UriFromIif(storage, instanceIdentifier, nodeId);
        return FutureRestRequest.createFutureRequest(this, uri, "GET", (String) null, this.headers,
                instanceIdentifier.getTargetType());
    }

    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> read(LogicalDatastoreType store,
            InstanceIdentifier<T> instanceIdentifier)
            throws ClassNotFoundException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, IOException {
        return this.read(store, instanceIdentifier, null);
    }

    protected <T extends DataObject> String getRfc8040UriFromIif(LogicalDatastoreType storage,
            InstanceIdentifier<T> instanceIdentifier, String nodeId) throws ClassNotFoundException,
            NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String uri = "";
        Iterable<PathArgument> iterable = instanceIdentifier.getPathArguments();
        Iterator<PathArgument> it = iterable.iterator();
        boolean isNewModule = true;
        String moduleName = null;
        while (it.hasNext()) {
            PathArgument pa = it.next();
            Class<?> cls = Class.forName(pa.getType().getName());
            String key = getKeyOrNull(pa);
            Field qname = cls.getField("QNAME");
            QName value = (QName) qname.get(cls);
            if (!implementsChildOf(cls)) {
                continue;
            }
            if (isNewModule) {
                moduleName = value.getLocalName();
            }
            if (key == null) {
                uri += String.format("/%s%s", isNewModule ? (moduleName + ":") : "",
                        value.getLocalName());
            } else {
                uri += String.format("/%s=%s", value.getLocalName(), key);
            }
            isNewModule = false;
        }
        return this.getRfc8040UriFromIif(storage, uri, nodeId);
    }

    protected <T extends DataObject> String getRfc8040UriFromIif(LogicalDatastoreType storage,
            String postUri, String nodeId) {
        if (!postUri.startsWith("/")) {
            postUri = "/" + postUri;
        }
        final String sstore = storage == LogicalDatastoreType.CONFIGURATION ? "data" : "operations";
        return nodeId != null ? String.format(
                "/rests/%s/network-topology:network-topology/topology=topology-netconf/node=%s/yang-ext:mount%s",
                sstore, nodeId, postUri) : String.format("/rests/%s%s", sstore, postUri);
    }

    private static boolean implementsChildOf(Class<?> clazz) {

        Class<?>[] clazzes = clazz.getInterfaces();
        for (Class<?> cls : clazzes) {
            if (cls.equals(ChildOf.class)) {
                return true;
            }
        }
        return false;
    }

    private String getKeyOrNull(PathArgument pa) {
        try {
            Class<? extends PathArgument> clazz = pa.getClass();

            Field field = clazz.getDeclaredField("key");
            field.setAccessible(true);
            Object keydef = field.get(pa);
            if (keydef instanceof Identifier) {
                // ((Identifier)keydef).
                Field[] fields = keydef.getClass().getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    Object key = f.get(keydef);
                    if (key instanceof Uri) {
                        return ((Uri) key).getValue();
                    }
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            LOG.debug("key is not a field of class {}", pa.getClass());
        }
        return null;
    }

    private static String assembleExceptionMessage(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);

        StringBuffer buf = new StringBuffer();
        buf.append("Exception: ");
        buf.append(sw.toString());
        return buf.toString();
    }

    public <O extends DataObject, I extends DataObject> ListenableFuture<RpcResult<O>> executeRpc(
            String nodeId, String rpc, I input, Class<O> clazz) {
        RpcResultBuilder<O> result;
        try {

            BaseHTTPResponse response = this.sendRequest(
                    this.getRfc8040UriFromIif(LogicalDatastoreType.OPERATIONAL, rpc, nodeId), "POST",
                    input == null ? "" : this.mapper.writeValueAsString(input), this.headers,
                    DEFAULT_TIMEOUT);
            if (response.isSuccess()) {
                Builder<O> outputBuilder = this.mapper.readerFor(clazz).readValue(response.body);
                result = RpcResultBuilder.success(outputBuilder);
            } else {
                result = RpcResultBuilder.failed();
            }
        } catch (IOException e) {
            LOG.info("Exception", e);
            result = RpcResultBuilder.failed();
            result.withError(ErrorType.APPLICATION, assembleExceptionMessage(e));
        }

        return result.buildFuture();
    }

}
