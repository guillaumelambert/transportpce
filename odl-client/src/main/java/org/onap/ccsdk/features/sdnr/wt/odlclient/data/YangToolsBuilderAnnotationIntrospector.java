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
import java.util.HashMap;
import java.util.Map;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapper.DateAndTimeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.NetconfNodeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.circuit.pack.PortsBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.circuit.pack.ports.OtdrPortBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.degree.ConnectionPortsBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.interfaces.grp.InterfaceBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.mc.capabilities.g.McCapabilitiesBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.ConnectionMapBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.DegreeBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.InfoBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.LineAmplifierBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.OduSwitchingPoolsBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.SharedRiskGroupBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.XponderBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.CircuitPackBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.NonBlockingListBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.srg.CircuitPacksBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.xponder.XpdrPortBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.lldp.rev181019.lldp.container.lldp.GlobalConfigBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.maintenance.testsignal.rev171215.maint.testsignal.MaintTestsignalBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.odu.attributes.TcmBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.odu.container.OduBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.opu.opu.msi.ExpMsiBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.opu.opu.msi.RxMsiBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.opu.opu.msi.TxMsiBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.parent.odu.allocation.ParentOduAllocationBuilder;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.otu.interfaces.rev181019.otu.container.OtuBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.Ports;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.OtdrPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.ConnectionPorts;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.interfaces.grp.Interface;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.mc.capabilities.g.McCapabilities;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.ConnectionMap;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Degree;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Info;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.LineAmplifier;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.OduSwitchingPools;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.SharedRiskGroup;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Xponder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.CircuitPack;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.NonBlockingList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.srg.CircuitPacks;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.xponder.XpdrPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.lldp.container.lldp.GlobalConfig;
import org.opendaylight.yang.gen.v1.http.org.openroadm.maintenance.testsignal.rev171215.maint.testsignal.MaintTestsignal;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.odu.attributes.Tcm;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.odu.container.Odu;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.opu.opu.msi.ExpMsi;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.opu.opu.msi.RxMsi;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.opu.opu.msi.TxMsi;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.parent.odu.allocation.ParentOduAllocation;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.otu.interfaces.rev181019.otu.container.Otu;
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
        this.customDeserializer.put(OduSwitchingPools.class,OduSwitchingPoolsBuilder.class.getName());
        this.customDeserializer.put(NonBlockingList.class,NonBlockingListBuilder.class.getName());
        this.customDeserializer.put(ConnectionMap.class,ConnectionMapBuilder.class.getName());
        this.customDeserializer.put(LineAmplifier.class,LineAmplifierBuilder.class.getName());
        this.customDeserializer.put(CircuitPack.class,CircuitPackBuilder.class.getName());
        this.customDeserializer.put(Xponder.class,XponderBuilder.class.getName());
        this.customDeserializer.put(XpdrPort.class,XpdrPortBuilder.class.getName());
        this.customDeserializer.put(Odu.class,OduBuilder.class.getName());
        this.customDeserializer.put(ParentOduAllocation.class,ParentOduAllocationBuilder.class.getName());
        this.customDeserializer.put(TxMsi.class,TxMsiBuilder.class.getName());
        this.customDeserializer.put(RxMsi.class,RxMsiBuilder.class.getName());
        this.customDeserializer.put(ExpMsi.class,ExpMsiBuilder.class.getName());
        this.customDeserializer.put(MaintTestsignal.class,MaintTestsignalBuilder.class.getName());
        this.customDeserializer.put(Tcm.class,TcmBuilder.class.getName());
        this.customDeserializer.put(Otu.class,OtuBuilder.class.getName());


    }
    @Override
    public Class<?> findPOJOBuilder(AnnotatedClass ac) {
        try {
            String builder = null;
            if (this.customDeserializer.containsKey(ac.getRawType())) {
                builder = this.customDeserializer.get(ac.getRawType());
            }
            else {
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

    public Class<?> findClass(String name) throws ClassNotFoundException {
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
