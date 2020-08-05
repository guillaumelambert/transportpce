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
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.DateAndTimeBuilder;

import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.NetconfNodeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.circuit.pack.PortsBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.circuit.pack.ports.OtdrPortBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.degree.ConnectionPortsBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.interfaces.grp.InterfaceBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.mc.capabilities.g.McCapabilitiesBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.DegreeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.InfoBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.SharedRiskGroupBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.srg.CircuitPacksBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.lldp.rev181019.lldp.container.lldp.GlobalConfigBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.Ports;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.OtdrPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.ConnectionPorts;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.interfaces.grp.Interface;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.mc.capabilities.g.McCapabilities;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Degree;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Info;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.SharedRiskGroup;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.srg.CircuitPacks;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.EthernetCsmacd;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.InterfaceType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.lldp.container.lldp.GlobalConfig;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNode;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YangToolsBuilderAnnotationIntrospector extends JacksonAnnotationIntrospector {

    private static final Logger LOG = LoggerFactory.getLogger(YangToolsBuilderAnnotationIntrospector.class);
    private static final long serialVersionUID = 1L;
    private final BundleContext context;
    private final Map<Class<?>,String> customDeserializer;

    public YangToolsBuilderAnnotationIntrospector(BundleContext context) {
        this.context = context;
        this.customDeserializer = new HashMap<>();
        //this.customDeserializer.put(Credentials.class, LoginPasswordBuilder.class.getName());
        this.customDeserializer.put(DateAndTime.class,DateAndTimeBuilder.class.getName());
        this.customDeserializer.put(Info.class,InfoBuilder.class.getName());
        this.customDeserializer.put(GlobalConfig.class,GlobalConfigBuilder.class.getName());
        this.customDeserializer.put(Degree.class,DegreeBuilder.class.getName());
        this.customDeserializer.put(NetconfNode.class,NetconfNodeBuilder.class.getName());
        this.customDeserializer.put(SharedRiskGroup.class,SharedRiskGroupBuilder.class.getName());
        this.customDeserializer.put(McCapabilities.class,McCapabilitiesBuilder.class.getName());
        this.customDeserializer.put(CircuitPacks.class,CircuitPacksBuilder.class.getName());
        this.customDeserializer.put(ConnectionPorts.class,ConnectionPortsBuilder.class.getName());
        this.customDeserializer.put(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.
                CircuitPacks.class,org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.
                degree.CircuitPacksBuilder.class.getName());
        this.customDeserializer.put(Interface.class,InterfaceBuilder.class.getName());
        this.customDeserializer.put(OtdrPort.class,OtdrPortBuilder.class.getName());
        this.customDeserializer.put(Ports.class,PortsBuilder.class.getName());

    }
    @Override
    public Class<?> findPOJOBuilder(AnnotatedClass ac) {
        try {
            String builder = null;
            if (this.customDeserializer.containsKey(ac.getRawType())) {
                builder = this.customDeserializer.get(ac.getRawType());
            } else if(ac.getRawType() == Class.class){
                //for(TypeVariable<?> i : ac.getRawType().)
                LOG.info("comp type={}",ac.getRawType().isAssignableFrom(EthernetCsmacd.class));
            }
            else{
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
