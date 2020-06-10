/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.restconf;

import com.google.common.util.concurrent.FluentFuture;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.odlclient.config.RemoteOdlConfig.AuthMethod;
import org.onap.ccsdk.features.sdnr.wt.odlclient.http.BaseHTTPClient;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Uri;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Identifier;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.common.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestconfHttpClient extends BaseHTTPClient {

    private static final Logger LOG = LoggerFactory.getLogger(RestconfHttpClient.class);
    private final Map<String, String> jsonHeaders;

    public RestconfHttpClient(String base, boolean trustAllCerts, AuthMethod authMethod, String username,
            String password) throws Exception {
        super(base, trustAllCerts);
        if (authMethod == AuthMethod.TOKEN) {
            throw new Exception("not yet implemented");
        }
        this.jsonHeaders = new HashMap<>();
        this.jsonHeaders.put("Content-Type", "application/json");
        this.jsonHeaders.put("Authorization",
                BaseHTTPClient.getAuthorizationHeaderValue(username, password));
        this.jsonHeaders.put("Accept", "application/xml");
    }

//    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> readConfig(
//            InstanceIdentifier<T> instanceIdentifier, String nodeId)
//            throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException,
//            IllegalArgumentException, IllegalAccessException {
//        return this.read(LogicalDatastoreType.CONFIGURATION, instanceIdentifier, nodeId);
//    }
//
//    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> readOperational(
//            InstanceIdentifier<T> instanceIdentifier, String nodeId)
//            throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException,
//            IllegalArgumentException, IllegalAccessException {
//        return this.read(LogicalDatastoreType.OPERATIONAL, instanceIdentifier, nodeId);
//    }
//
//    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> readConfig(
//            InstanceIdentifier<T> instanceIdentifier) throws IOException, ClassNotFoundException,
//            NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//        return this.read(LogicalDatastoreType.CONFIGURATION, instanceIdentifier, null);
//    }
//
//    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> readOperational(
//            InstanceIdentifier<T> instanceIdentifier) throws IOException, ClassNotFoundException,
//            NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//        return this.read(LogicalDatastoreType.OPERATIONAL, instanceIdentifier, null);
//    }

    public <T extends DataObject> @NonNull FluentFuture<Optional<T>> read(LogicalDatastoreType storage,
            InstanceIdentifier<T> instanceIdentifier, String nodeId)
            throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        final String uri = this.getRfc8040UriFromIif(storage, instanceIdentifier, nodeId);
        return FutureRestRequest.createFutureRequest(this, uri, "GET", (String) null, this.jsonHeaders,
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
        final String sstore = storage == LogicalDatastoreType.CONFIGURATION ? "config" : "data";
        String uri = nodeId != null ? String.format(
                "/rests/%s/network-topology:network-topology/topology=topology-netconf/node=%s/yang-ext:mount",
                sstore, nodeId) : String.format("/rests/%s", sstore);
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
        return uri;
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
            LOG.warn("problem while getting key: {}", e);
        }
        return null;
    }

    private <T extends DataObject> String uriFromIif(String storage,
            InstanceIdentifier<T> instanceIdentifier) {
        String uri = "/" + storage;
        Iterable<PathArgument> iterable = instanceIdentifier.getPathArguments();
        Iterator<PathArgument> it = iterable.iterator();
        while (it.hasNext()) {
            PathArgument pa = it.next();
        }
        return uri;
    }

}
