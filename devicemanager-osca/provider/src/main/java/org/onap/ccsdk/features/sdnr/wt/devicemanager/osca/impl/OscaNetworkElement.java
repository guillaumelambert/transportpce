/*******************************************************************************
 * ============LICENSE_START========================================================================
 * ONAP : ccsdk feature sdnr wt
 * =================================================================================================
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property. All rights reserved.
 * =================================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * ============LICENSE_END==========================================================================
 ******************************************************************************/
package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl;

import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.dataprovider.model.DataProvider;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.ne.service.NetworkElement;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.ne.service.NetworkElementService;
import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.NetconfAccessor;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.Alarm;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.hardware.rev180313.Hardware;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.NetworkElementDeviceType;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.NotificationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class OscaNetworkElement implements NetworkElement {

	private static final Logger log = LoggerFactory.getLogger(OscaNetworkElement.class);

	private final NetconfAccessor netconfAccessor;

	private final DataProvider databaseService;

//    private final OscaToInternalDataModel oScaMapper;
//    
//    public final PmdataEntity oScaPmDataEntity;   
//    
//    private final PmdataEntityBuilder oScaPmDataModel;
//    private List<PmdataEntity> pmDataEntries = null;

	private ListenerRegistration<NotificationListener> oScaListenerRegistrationResult;
	private @NonNull final OscaChangeNotificationListener oScaListener;
	private ListenerRegistration<NotificationListener> oScaFaultListenerRegistrationResult;
	private @NonNull OscaFaultNotificationListener oScaFaultListener;

	OscaNetworkElement(NetconfAccessor netconfAccess, DataProvider databaseService) {
        
		log.info("Create {}",OscaNetworkElement.class.getSimpleName());
        this.netconfAccessor = netconfAccess;
        this.databaseService = databaseService;
        this.oScaListenerRegistrationResult = null;
        this.oScaListener = new OscaChangeNotificationListener(netconfAccessor, databaseService);
        this.oScaFaultListenerRegistrationResult = null;
        this.oScaFaultListener = new OscaFaultNotificationListener();
//        this.oScaMapper = new OscaToInternalDataModel();
//        this.oScaPmDataModel =new PmdataEntityBuilder();
//        this.oScaPmDataEntity = oScaPmDataModel.build();

   
    }

//    public void readPmData() {
//    	if(this.netconfAccessor.getCapabilites().isSupportingNamespace(PmdataEntity.QNAME)) {
//
//    		pmDataEntries.add(oScaPmDataEntity);
//    		databaseService.doWritePerformanceData(pmDataEntries);
//    	}
//    }
	


	public void initialReadFromNetworkElement() {

	
		/*
		 * Hardware hardware = readHardware(netconfAccessor); if (hardware != null) {
		 * List<Component> componentList = hardware.getComponent(); if (componentList !=
		 * null) { for (Component component : componentList) {
		 * databaseService.writeInventory(
		 * oRanMapper.getInternalEquipment(netconfAccessor.getNodeId(), component)); } }
		 * }
		 */

	}


	@Override
	public NetworkElementDeviceType getDeviceType() {
		return NetworkElementDeviceType.Optical;
	}

	@Override
	public void register() {
		initialReadFromNetworkElement();

		this.oScaListenerRegistrationResult = netconfAccessor.doRegisterNotificationListener(oScaListener);
		this.oScaFaultListenerRegistrationResult = netconfAccessor.doRegisterNotificationListener(oScaFaultListener);
		// Register netconf stream
		netconfAccessor.registerNotificationsStream(NetconfAccessor.DefaultNotificationsStream);
		


	}

		

	@Override
	public void deregister() {
		if (oScaListenerRegistrationResult != null) {
			this.oScaListenerRegistrationResult.close();
		}
		if (oScaFaultListenerRegistrationResult != null) {
			this.oScaFaultListenerRegistrationResult.close();
		}
		;
	}

	@Override
	public NodeId getNodeId() {
		return netconfAccessor.getNodeId();
	}

	@Override
	public <L extends NetworkElementService> Optional<L> getService(Class<L> clazz) {
		return Optional.empty();
	}

	@Override
	public void warmstart() {
	}

	@Override
	public Optional<NetconfAccessor> getAcessor() {
		return Optional.of(netconfAccessor);
	}

}
