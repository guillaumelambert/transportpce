/*
 * ============LICENSE_START=======================================================
 * ONAP : ccsdk features
 * ================================================================================
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 * All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 *
 */
package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl;

import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.NetconfAccessor;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.shelves.Shelves;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.OrgOpenroadmDevice;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.circuit.packs.CircuitPacks;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.Inventory;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.InventoryBuilder;

import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev191129.interfaces.grp.Interface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shabnam
 *
 */
public class OscaInventoryInput {

	private OrgOpenroadmDevice openRoadmDevice;
	private final NetconfAccessor accessor;

	/**
	 * @param netconfAccessor
	 * @param readDevice
	 */
	public OscaInventoryInput(NetconfAccessor netconfAccessor, OrgOpenroadmDevice readDevice) {
		this.openRoadmDevice = readDevice;
		this.accessor = netconfAccessor;
	}

	private static final Logger log = LoggerFactory.getLogger(OscaInventoryInput.class);
	

	public Inventory getInventoryData(long treeLevel) {
		InventoryBuilder inventoryBuilder = new InventoryBuilder();
		inventoryBuilder.setNodeId(this.accessor.getNodeId().getValue())
				.setUuid(this.openRoadmDevice.getInfo().getNodeId().getValue())
				.setDate(this.openRoadmDevice.getInfo().getCurrentDatetime().getValue())
				.setId(this.openRoadmDevice.getInfo().getNodeId().getValue())
				.setManufacturerIdentifier(this.openRoadmDevice.getInfo().getVendor())
				.setModelIdentifier(this.openRoadmDevice.getInfo().getModel())
				.setSerial(this.openRoadmDevice.getInfo().getSerialId()).setTreeLevel(treeLevel)
				.setVersion(this.openRoadmDevice.getInfo().getOpenroadmVersion().getName())
				.setDescription("org-openroadm-device").setParentUuid("None")
				.setTypeName(this.openRoadmDevice.getInfo().getNodeType().getName()).setPartTypeId("device");
//		log.info("Inventory data written for {}", this.openRoadmDevice.getInfo().getNodeId().getValue());
		return inventoryBuilder.build();
	}

	public Inventory getShelvesInventory(Shelves shelf) {
		InventoryBuilder inventoryBuilder = new InventoryBuilder();

		return inventoryBuilder.build();
	}

	public Inventory getInterfacesInventory(Interface deviceInterface, long treeLevel) {
		InventoryBuilder inventoryBuilder = new InventoryBuilder();
		inventoryBuilder.setNodeId(this.accessor.getNodeId().getValue()).setId(deviceInterface.getName())
				.setDescription((deviceInterface.getDescription() == null) ? "N/A" : deviceInterface.getDescription())
				.setUuid(deviceInterface.getName()).setSerial(deviceInterface.getName())
				.setParentUuid(deviceInterface.getSupportingCircuitPackName()).setTreeLevel(treeLevel)
				.setTypeName(
						deviceInterface.getType().getName().substring(69, deviceInterface.getType().getName().length()))
				.setPartTypeId("Interface").setManufacturerIdentifier(this.openRoadmDevice.getInfo().getVendor())
				.setModelIdentifier(this.openRoadmDevice.getInfo().getModel())
				.setVersion("N/A")
				.setDate(this.openRoadmDevice.getInfo().getCurrentDatetime().getValue());
//		log.info("Inventory data written for {}", deviceInterface.getName());

		return inventoryBuilder.build();
	}

	public Inventory getCircuitPackInventory(CircuitPacks circuitPack, long treeLevel) {
		InventoryBuilder inventoryBuilder = new InventoryBuilder();
		inventoryBuilder.setNodeId(this.accessor.getNodeId().getValue()).setUuid(circuitPack.getCircuitPackName())
				.setDate((circuitPack.getManufactureDate() == null) ? "N/A"
						: circuitPack.getManufactureDate().getValue().substring(0, 19))
				.setId(circuitPack.getCircuitPackName()).setManufacturerIdentifier(circuitPack.getVendor())
				.setModelIdentifier(circuitPack.getModel()).setSerial(circuitPack.getSerialId()).setTreeLevel(treeLevel)
				.setVersion(circuitPack.getHardwareVersion())
				.setDescription("ProductCode: " + circuitPack.getProductCode() + "  " + "Mode: "
						+ circuitPack.getCircuitPackMode())
				.setTypeName((circuitPack.getType() == null) ? circuitPack.getCircuitPackType() : circuitPack.getType())
				.setPartTypeId((circuitPack.getClei()== null)? circuitPack.getType():circuitPack.getClei())
				.setParentUuid((circuitPack.getParentCircuitPack() == null)
						? this.openRoadmDevice.getInfo().getNodeId().getValue()
						: circuitPack.getParentCircuitPack().getCircuitPackName());
//		log.info("Inventory data written for {}", circuitPack.getCircuitPackName());

		return inventoryBuilder.build();
	}
}
