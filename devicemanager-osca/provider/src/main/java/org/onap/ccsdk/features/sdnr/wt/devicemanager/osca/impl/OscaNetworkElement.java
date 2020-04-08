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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;

import org.onap.ccsdk.features.sdnr.wt.dataprovider.model.DataProvider;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.ne.service.NetworkElement;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.ne.service.NetworkElementService;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.DeviceManagerServiceProvider;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.FaultService;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.PerformanceManager;
import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.NetconfAccessor;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.OrgOpenroadmDevice;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.circuit.packs.CircuitPacks;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.interfaces.grp.Interface;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.org.openroadm.device.Xponder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.shelf.Slots;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.shelves.Shelves;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.NetworkElementDeviceType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.PmdataEntity;
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
	private final @NonNull FaultService faultEventListener;


	private final long equipmentLevel = 0;
	private Hashtable<String, Long> circuitPacksRecord = new Hashtable<String, Long>();
	private ListenerRegistration<NotificationListener> oScaListenerRegistrationResult;
	private @NonNull final OscaChangeNotificationListener oScaListener;
	private ListenerRegistration<NotificationListener> oScaFaultListenerRegistrationResult;
	private @NonNull OscaFaultNotificationListener oScaFaultListener;
	private OscaInventoryInput oscaInventoryInput;
	private PmDataBuilderOpenRoadm openRoadmPmData;
	private Integer sequenceNumber = 1;;
	private List<PmdataEntity> pmDataEntity = new ArrayList<PmdataEntity>();

	public OscaNetworkElement(NetconfAccessor netconfAccess, DeviceManagerServiceProvider serviceProvider) {

		log.info("Create {}", OscaNetworkElement.class.getSimpleName());
		this.netconfAccessor = netconfAccess;
		this.databaseService = serviceProvider.getDataProvider();
		this.oScaListenerRegistrationResult = null;
		this.oScaListener = new OscaChangeNotificationListener(netconfAccessor, databaseService);
		this.oScaFaultListenerRegistrationResult = null;
		this.oScaFaultListener = new OscaFaultNotificationListener(netconfAccessor, databaseService);
		this.oscaInventoryInput = new OscaInventoryInput(netconfAccess, readDevice(netconfAccess));
		this.faultEventListener = serviceProvider.getFaultService();

		this.openRoadmPmData = new PmDataBuilderOpenRoadm(this.netconfAccessor, serviceProvider.getDataProvider());

		log.info("NodeId {}", this.netconfAccessor.getNodeId().getValue());

		log.info("oScaMapper details{}", this.oscaInventoryInput.getClass().getName());

//        this.oScaPmDataModel =new PmdataEntityBuilder();
//        this.oScaPmDataEntity = oScaPmDataModel.build();

	}

	public void initialReadFromNetworkElement() {

		OrgOpenroadmDevice device = readDevice(this.netconfAccessor);

		databaseService.writeInventory(this.oscaInventoryInput.getInventoryData(equipmentLevel));

		readShelvesData(device);
		readXpndrData(device);
		readCircuitPacketData(device);
		readInterfaceData(device);
		faultEventListener.initCurrentProblemStatus(this.netconfAccessor.getNodeId(),
				oScaFaultListener.writeFaultData(this.sequenceNumber));
		oScaFaultListener.writeFaultLog(oScaFaultListener.writeFaultData(this.sequenceNumber));
		this.sequenceNumber = this.sequenceNumber + 1;

		pmDataEntity=this.openRoadmPmData.buildPmDataEntity(this.openRoadmPmData.getPmData(this.netconfAccessor));
		if(!pmDataEntity.isEmpty()) {
			this.databaseService.doWritePerformanceData(pmDataEntity);
			log.info("PmDatEntity is written with size {}", pmDataEntity.size());
			for(PmdataEntity ent: pmDataEntity) {
				log.info("GetNode: {}, granPeriod: {}", ent.getNodeName(), ent.getGranularityPeriod().getName());
			}
		}
		else {
			log.info("PmDatEntity is empty");
		}
		


	}

	private void readShelvesData(OrgOpenroadmDevice device) {
		List<Shelves> shelves = device.getShelves();
		if (shelves != null) {
			for (Shelves shelf : shelves) {
				log.info(
						"Shelf Name: {}, \n Serial Id:{}, \n Product Code;{}, \n Position:{}, \n EquipmetState: {}, \n Hardware version: {}"
								+ "\n ShelfType:{}, \n Vendor: {}, \n LifecycleState: {} ",
						shelf.getShelfName(), shelf.getSerialId(), shelf.getProductCode(), shelf.getShelfPosition(),
						shelf.getEquipmentState(), shelf.getHardwareVersion(), shelf.getShelfType(), shelf.getVendor(),
						shelf.getLifecycleState());
				List<Slots> slotList = shelf.getSlots();
				if (slotList != null) {
					for (Slots slot : slotList) {
						log.info("Slots for the shelf: {}", shelf.getShelfName());
						log.info("\n Slot Name: {}, \n Status: {}, \n Slot label: {} ", slot.getSlotName(),
								slot.getSlotStatus(), slot.getLabel());
					}
				}

			}

		}
	}

	private void readXpndrData(OrgOpenroadmDevice device) {
		List<Xponder> xponderList = device.getXponder();
		if (xponderList != null) {
			for (Xponder xponder : xponderList) {
				log.info("Xponders: No.: {} , \n Port: {} ,\n Type: {}", xponder.getXpdrNumber(), xponder.getXpdrPort(),
						xponder.getXpdrType());
			}

		}
	}

	private void readCircuitPacketData(OrgOpenroadmDevice device) {
		List<CircuitPacks> circuitpacklist = device.getCircuitPacks();
		if (circuitpacklist != null) {
			for (CircuitPacks cp : circuitpacklist) {
//				log.info("CP Name:{}", cp.getCircuitPackName());
				if (cp.getParentCircuitPack() != null) {
					circuitPacksRecord.put(cp.getCircuitPackName(), (equipmentLevel + 2));
					databaseService
							.writeInventory(this.oscaInventoryInput.getCircuitPackInventory(cp, equipmentLevel + 2));
				} else {
					circuitPacksRecord.put(cp.getCircuitPackName(), (equipmentLevel + 1));
					databaseService
							.writeInventory(this.oscaInventoryInput.getCircuitPackInventory(cp, equipmentLevel + 1));
				}
			}
		}

	}

	private void readInterfaceData(OrgOpenroadmDevice device) {
		List<Interface> interfaceList = device.getInterface();
		if (interfaceList != null) {
			for (Interface deviceInterface : interfaceList) {

//				log.info("\n InterfaceName: {}", deviceInterface.getName());
				if (circuitPacksRecord.containsKey(deviceInterface.getSupportingCircuitPackName())) {
					databaseService.writeInventory(this.oscaInventoryInput.getInterfacesInventory(deviceInterface,
							circuitPacksRecord.get(deviceInterface.getSupportingCircuitPackName()) + 1));
				} else {
					databaseService.writeInventory(
							this.oscaInventoryInput.getInterfacesInventory(deviceInterface, equipmentLevel + 1));
				}
			}
		}

	}

	private OrgOpenroadmDevice readDevice(NetconfAccessor accessor) {

		final Class<OrgOpenroadmDevice> openRoadmDev = OrgOpenroadmDevice.class;
		log.info("DBRead Get Device for class {} from mountpoint {} for uuid {}",
				OrgOpenroadmDevice.class.getSimpleName(), accessor.getMountpoint().getClass().getName(),
				accessor.getNodeId().getValue());

		InstanceIdentifier<OrgOpenroadmDevice> deviceId = InstanceIdentifier.builder(openRoadmDev).build();

		OrgOpenroadmDevice device = accessor.getTransactionUtils().readData(accessor.getDataBroker(),
				LogicalDatastoreType.OPERATIONAL, deviceId);

		return device;

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
//		 Register netconf stream
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
