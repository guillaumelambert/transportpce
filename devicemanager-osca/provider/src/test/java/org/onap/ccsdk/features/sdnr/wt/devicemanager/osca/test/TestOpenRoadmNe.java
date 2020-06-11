//package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.test;
///*
// * ============LICENSE_START========================================================================
// * ONAP : ccsdk feature sdnr wt
// * =================================================================================================
// * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property. All rights reserved.
// * =================================================================================================
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
// * in compliance with the License. You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software distributed under the License
// * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
// * or implied. See the License for the specific language governing permissions and limitations under
// * the License.
// * ============LICENSE_END==========================================================================
// */
//
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//import org.eclipse.jdt.annotation.Nullable;
//import org.junit.Before;
//import org.junit.Test;
//import org.onap.ccsdk.features.sdnr.wt.devicemanager.ne.service.NetworkElement;
//import org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl.OscaNetworkElement;
//import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.DeviceManagerServiceProvider;
//import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.EquipmentService;
//import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.FaultService;
//import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.NetconfAccessor;
//import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.TransactionUtils;
//import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.Capabilities;
//import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
//import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.OrgOpenroadmDevice;
//import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
//
//import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.EventlogEntity;
//import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.SourceType;
//import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
//import org.opendaylight.yangtools.yang.binding.Augmentation;
//import org.opendaylight.yangtools.yang.binding.DataContainer;
//import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
//
//public class TestOpenRoadmNe {
//
//    NetconfAccessor accessor;
//    DeviceManagerServiceProvider serviceProvider;
//    Capabilities capabilities;
//    TransactionUtils transactionUtils;
//    OscaNetworkElement optionalNe;
//    FaultService faultService;
//    @Before
//    public void init() {
//        accessor = mock(NetconfAccessor.class);
//        serviceProvider = mock(DeviceManagerServiceProvider.class);
//        capabilities = mock(Capabilities.class);
//        transactionUtils = mock(TransactionUtils.class);
//
//        faultService = mock(FaultService.class);
//
//
//        when(accessor.getCapabilites()).thenReturn(capabilities);
//        when(serviceProvider.getFaultService()).thenReturn(faultService);
//
//
//        NodeId nNodeId = new NodeId("RoadmA");
//        when(accessor.getNodeId()).thenReturn(nNodeId);
//        when(accessor.getCapabilites().isSupportingNamespaceAndRevision(OrgOpenroadmDevice.QNAME)).thenReturn(true);
//        when(accessor.getTransactionUtils()).thenReturn(transactionUtils);
//
//    }
//    @Test
//    public void test() {
//        optionalNe = new OscaNetworkElement(accessor,serviceProvider);
//        
//
//    }
//    
//        }
//
//
