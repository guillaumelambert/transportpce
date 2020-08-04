/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.DateAndTimeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.DegreeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.GlobalConfigBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.InfoBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Degree;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Info;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.lldp.container.lldp.GlobalConfig;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.credentials.Credentials;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YangToolsBuilderAnnotationIntrospector extends JacksonAnnotationIntrospector {

    private static final Logger LOG = LoggerFactory.getLogger(YangToolsBuilderAnnotationIntrospector.class);
    private static final long serialVersionUID = 1L;
    private final BundleContext context;

    public YangToolsBuilderAnnotationIntrospector(BundleContext context) {
        this.context = context;
    }
    @Override
    public Class<?> findPOJOBuilder(AnnotatedClass ac) {
        try {
            String builder = null;
            if (ac.getRawType().equals(Credentials.class)) {
                builder = "org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114."
                        + "netconf.node.credentials.credentials.LoginPasswordBuilder";
            } else if (ac.getRawType().equals(DateAndTime.class)) {
                builder = DateAndTimeBuilder.class.getName();
            } else if (ac.getRawType().equals(Info.class)) {
                builder = InfoBuilder.class.getName();
            } else if (ac.getRawType().equals(GlobalConfig.class)) {
                builder = GlobalConfigBuilder.class.getName();
            }else if (ac.getRawType().equals(Degree.class)) {
                builder = DegreeBuilder.class.getName();
            } else {
                if (ac.getRawType().isInterface()) {
                    builder = ac.getName() + "Builder";
                }
            }
            if (builder != null) {
                LOG.trace("map {} with builder {}", ac.getName(), builder);
                Class<?> innerBuilder = findClass(builder);
                return innerBuilder;
            }
        } catch (ClassNotFoundException e) {
            LOG.trace("builder class not found for {}",ac.getName());
        }
        return super.findPOJOBuilder(ac);
    }

    @Override
    public Value findPOJOBuilderConfig(AnnotatedClass ac) {
        if (ac.hasAnnotation(JsonPOJOBuilder.class)) {
            return super.findPOJOBuilderConfig(ac);
        }
        return new JsonPOJOBuilder.Value("build", "set");
    }

    Class<?> findClass(String name) throws ClassNotFoundException {
        return findClass(name, context);
    }

    public Class<?> findClass(String name, Class<?> clazz) throws ClassNotFoundException {
        Bundle bundle = FrameworkUtil.getBundle(clazz);
        BundleContext ctx = bundle != null ? bundle.getBundleContext() : null;
        return findClass(name, ctx);
    }

    public Class<?> findClass(String name, BundleContext context) throws ClassNotFoundException {
        // Try to find in other bundles
        if (context != null) {
            //OSGi environment
            for (Bundle b : context.getBundles()) {
                try {
                    LOG.trace("try to find class {} in bundle {}", name, b.getSymbolicName());
                    return b.loadClass(name);
                } catch (ClassNotFoundException e) {
                    // No problem, this bundle doesn't have the class
                }
            }
            throw new ClassNotFoundException("Can not find Class in OSGi context.");
        } else {
            return Class.forName(name);
        }
        // not found in any bundle
    }
}
