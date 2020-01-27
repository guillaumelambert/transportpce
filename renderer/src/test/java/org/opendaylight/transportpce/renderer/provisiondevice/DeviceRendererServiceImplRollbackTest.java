/*
 * Copyright © 2018 Orange Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.transportpce.renderer.provisiondevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.opendaylight.controller.md.sal.binding.api.MountPoint;
import org.opendaylight.controller.md.sal.binding.api.MountPointService;
import org.opendaylight.transportpce.common.StringConstants;
import org.opendaylight.transportpce.common.crossconnect.CrossConnect;
import org.opendaylight.transportpce.common.crossconnect.CrossConnectImpl;
import org.opendaylight.transportpce.common.crossconnect.CrossConnectImpl121;
import org.opendaylight.transportpce.common.crossconnect.CrossConnectImpl221;
import org.opendaylight.transportpce.common.device.DeviceTransactionManager;
import org.opendaylight.transportpce.common.device.DeviceTransactionManagerImpl;
import org.opendaylight.transportpce.common.fixedflex.FixedFlexImpl;
import org.opendaylight.transportpce.common.fixedflex.FixedFlexInterface;
import org.opendaylight.transportpce.common.mapping.MappingUtils;
import org.opendaylight.transportpce.common.mapping.MappingUtilsImpl;
import org.opendaylight.transportpce.common.mapping.PortMapping;
import org.opendaylight.transportpce.common.mapping.PortMappingImpl;
import org.opendaylight.transportpce.common.mapping.PortMappingVersion121;
import org.opendaylight.transportpce.common.mapping.PortMappingVersion221;
import org.opendaylight.transportpce.common.openroadminterfaces.OpenRoadmInterfaceException;
import org.opendaylight.transportpce.common.openroadminterfaces.OpenRoadmInterfaces;
import org.opendaylight.transportpce.common.openroadminterfaces.OpenRoadmInterfacesImpl;
import org.opendaylight.transportpce.common.openroadminterfaces.OpenRoadmInterfacesImpl121;
import org.opendaylight.transportpce.common.openroadminterfaces.OpenRoadmInterfacesImpl221;
import org.opendaylight.transportpce.renderer.openroadminterface.OpenRoadmInterface121;
import org.opendaylight.transportpce.renderer.openroadminterface.OpenRoadmInterface221;
import org.opendaylight.transportpce.renderer.openroadminterface.OpenRoadmInterfaceFactory;
import org.opendaylight.transportpce.renderer.stub.MountPointServiceStub;
import org.opendaylight.transportpce.renderer.stub.MountPointStub;
import org.opendaylight.transportpce.renderer.utils.ServiceImplementationDataUtils;
import org.opendaylight.transportpce.test.AbstractTest;
import org.opendaylight.yang.gen.v1.http.org.opendaylight.transportpce.renderer.device.rev170228.RendererRollbackInput;
import org.opendaylight.yang.gen.v1.http.org.opendaylight.transportpce.renderer.device.rev170228.RendererRollbackInputBuilder;
import org.opendaylight.yang.gen.v1.http.org.opendaylight.transportpce.renderer.device.rev170228.RendererRollbackOutput;
import org.opendaylight.yang.gen.v1.http.org.transportpce.common.types.rev170907.node.interfaces.NodeInterface;
import org.opendaylight.yang.gen.v1.http.org.transportpce.common.types.rev170907.node.interfaces.NodeInterfaceBuilder;
import org.opendaylight.yang.gen.v1.http.org.transportpce.common.types.rev170907.node.interfaces.NodeInterfaceKey;

public class DeviceRendererServiceImplRollbackTest extends AbstractTest {

    private DeviceRendererService deviceRendererService;
    private CrossConnect crossConnect;
    private OpenRoadmInterfaces openRoadmInterfaces;
    private MappingUtils mappingUtils;
    private OpenRoadmInterfacesImpl121 openRoadmInterfacesImpl121;
    private OpenRoadmInterfacesImpl221 openRoadmInterfacesImpl221;
    private PortMappingVersion221 portMappingVersion22;
    private PortMappingVersion121 portMappingVersion121;
    private CrossConnectImpl121 crossConnectImpl121;
    private CrossConnectImpl221 crossConnectImpl221;

    private void setMountPoint(MountPoint mountPoint) {
        MountPointService mountPointService = new MountPointServiceStub(mountPoint);
        DeviceTransactionManager deviceTransactionManager = new DeviceTransactionManagerImpl(mountPointService, 3000);
        this.openRoadmInterfacesImpl121 = new OpenRoadmInterfacesImpl121(deviceTransactionManager);
        this.openRoadmInterfacesImpl221 = new OpenRoadmInterfacesImpl221(deviceTransactionManager);
        this.mappingUtils = new MappingUtilsImpl(getDataBroker());
        this.mappingUtils = Mockito.spy(this.mappingUtils);
        this.openRoadmInterfaces = new OpenRoadmInterfacesImpl(deviceTransactionManager, mappingUtils,
            openRoadmInterfacesImpl121, openRoadmInterfacesImpl221);
        this.openRoadmInterfaces = Mockito.spy(this.openRoadmInterfaces);
        this.portMappingVersion22 =
            new PortMappingVersion221(getDataBroker(), deviceTransactionManager, this.openRoadmInterfaces);
        this.portMappingVersion121 = new PortMappingVersion121(getDataBroker(), deviceTransactionManager,
            this.openRoadmInterfaces);
        this.crossConnectImpl121 = new CrossConnectImpl121(deviceTransactionManager);
        this.crossConnectImpl221 = new CrossConnectImpl221(deviceTransactionManager);
        this.crossConnect = new CrossConnectImpl(deviceTransactionManager, this.mappingUtils, this.crossConnectImpl121,
            this.crossConnectImpl221);
        this.crossConnect = Mockito.spy(this.crossConnect);
        PortMapping portMapping = new PortMappingImpl(getDataBroker(), this.portMappingVersion22,
            this.portMappingVersion121);
        FixedFlexInterface fixedFlexInterface = new FixedFlexImpl();
        OpenRoadmInterface121 openRoadmInterface121 = new OpenRoadmInterface121(portMapping,openRoadmInterfaces);
        OpenRoadmInterface221 openRoadmInterface221 = new OpenRoadmInterface221(portMapping,openRoadmInterfaces,
            fixedFlexInterface);
        OpenRoadmInterfaceFactory openRoadmInterfaceFactory = new OpenRoadmInterfaceFactory(this.mappingUtils,
            openRoadmInterface121,openRoadmInterface221);


        this.deviceRendererService = new DeviceRendererServiceImpl(this.getDataBroker(),
            deviceTransactionManager, openRoadmInterfaceFactory, this.openRoadmInterfaces, this.crossConnect,
            portMapping);
    }



    @Test
    public void testRollbackEmptyInterface() {
        setMountPoint(new MountPointStub(getDataBroker()));
        RendererRollbackInput rendererRollbackInput = ServiceImplementationDataUtils.buildRendererRollbackInput();
        RendererRollbackOutput rendererRollbackOutput =
            this.deviceRendererService.rendererRollback(rendererRollbackInput);
        Assert.assertTrue(rendererRollbackOutput.isSuccess());
        Assert.assertTrue(rendererRollbackOutput.getFailedToRollback().isEmpty());
    }

    @Test
    public void testRollbackConnectionIdNotExist() {
        setMountPoint(new MountPointStub(getDataBroker()));

        NodeInterfaceBuilder nodeInterfaceBuilder = new NodeInterfaceBuilder();
        nodeInterfaceBuilder.setNodeId("node1");
        nodeInterfaceBuilder.withKey(new NodeInterfaceKey("node1"));
        List<String> connectionID = new ArrayList<>();
        connectionID.add("node1-PP");
        nodeInterfaceBuilder.setConnectionId(connectionID);
        List<NodeInterface> nodeInterfaces = new ArrayList<>();
        nodeInterfaces.add(nodeInterfaceBuilder.build());
        RendererRollbackInputBuilder rendererRollbackInputBuilder = new RendererRollbackInputBuilder();
        rendererRollbackInputBuilder.setNodeInterface(nodeInterfaces);
        Mockito.doReturn(StringConstants.OPENROADM_DEVICE_VERSION_1_2_1).when(this.mappingUtils)
            .getOpenRoadmVersion("node1");
        RendererRollbackOutput rendererRollbackOutput =
            this.deviceRendererService.rendererRollback(rendererRollbackInputBuilder.build());
        Assert.assertFalse(rendererRollbackOutput.isSuccess());
        Assert.assertEquals(1, rendererRollbackOutput.getFailedToRollback().size());
        Assert.assertEquals("node1", rendererRollbackOutput.getFailedToRollback().get(0).getNodeId());
    }

    @Test
    public void testRollbackConnectionId() {
        setMountPoint(new MountPointStub(getDataBroker()));

        NodeInterfaceBuilder nodeInterfaceBuilder = new NodeInterfaceBuilder();
        nodeInterfaceBuilder.setNodeId("node1");
        nodeInterfaceBuilder.withKey(new NodeInterfaceKey("node1"));
        List<String> connectionID = new ArrayList<>();
        connectionID.add("src-PP-dest-PP-20");
        nodeInterfaceBuilder.setConnectionId(connectionID);
        List<NodeInterface> nodeInterfaces = new ArrayList<>();
        nodeInterfaces.add(nodeInterfaceBuilder.build());
        RendererRollbackInputBuilder rendererRollbackInputBuilder = new RendererRollbackInputBuilder();
        rendererRollbackInputBuilder.setNodeInterface(nodeInterfaces);

        Mockito.doReturn(Collections.emptyList()).when(this.crossConnect)
            .deleteCrossConnect("node1", connectionID.get(0));
        RendererRollbackOutput rendererRollbackOutput =
            this.deviceRendererService.rendererRollback(rendererRollbackInputBuilder.build());
        Assert.assertTrue("Rollback must success when cross connect returns true", rendererRollbackOutput.isSuccess());
        Assert.assertEquals(1, rendererRollbackOutput.getFailedToRollback().size());
        Assert.assertTrue("There must not be any failed interfaces when cross connect returns true",
            rendererRollbackOutput.getFailedToRollback().get(0).getInterface().isEmpty());

        Mockito.doReturn(null).when(this.crossConnect).deleteCrossConnect("node1", connectionID.get(0));
        rendererRollbackOutput =
            this.deviceRendererService.rendererRollback(rendererRollbackInputBuilder.build());
        Assert.assertFalse("Rollback must fail when cross connect returns false",rendererRollbackOutput.isSuccess());
        Assert.assertEquals(1, rendererRollbackOutput.getFailedToRollback().size());
        Assert.assertFalse(rendererRollbackOutput.getFailedToRollback().get(0).getInterface().isEmpty());
        Assert.assertEquals("node1", rendererRollbackOutput.getFailedToRollback().get(0).getNodeId());

        Mockito.verify(this.crossConnect, Mockito.times(2)).deleteCrossConnect("node1", connectionID.get(0));
    }

    @Test
    public void testRollbackInterfaces() throws OpenRoadmInterfaceException {
        setMountPoint(new MountPointStub(getDataBroker()));

        NodeInterfaceBuilder nodeInterfaceBuilder = new NodeInterfaceBuilder();
        nodeInterfaceBuilder.setNodeId("node1");
        nodeInterfaceBuilder.withKey(new NodeInterfaceKey("node1"));
        nodeInterfaceBuilder.setConnectionId(new ArrayList<>());
        List<String> oduInterfacesId = new ArrayList<>();
        oduInterfacesId.add("node1-" + StringConstants.NETWORK_TOKEN + "-ODU");
        nodeInterfaceBuilder.setOduInterfaceId(oduInterfacesId);
        List<String> otuInterfacesId = new ArrayList<>();
        otuInterfacesId.add("node1-" + StringConstants.NETWORK_TOKEN + "-OTU");
        nodeInterfaceBuilder.setOtuInterfaceId(otuInterfacesId);
        List<String> ochInterfacesId = new ArrayList<>();
        ochInterfacesId.add("node1-" + StringConstants.NETWORK_TOKEN + "-20");
        nodeInterfaceBuilder.setOchInterfaceId(ochInterfacesId);
        List<NodeInterface> nodeInterfaces = new ArrayList<>();
        nodeInterfaces.add(nodeInterfaceBuilder.build());
        RendererRollbackInputBuilder rendererRollbackInputBuilder = new RendererRollbackInputBuilder();
        rendererRollbackInputBuilder.setNodeInterface(nodeInterfaces);

        Mockito.doNothing().when(this.openRoadmInterfaces).deleteInterface(Mockito.eq("node1"), Mockito.anyString());
        RendererRollbackOutput rendererRollbackOutput =
            this.deviceRendererService.rendererRollback(rendererRollbackInputBuilder.build());
        Assert.assertTrue(rendererRollbackOutput.isSuccess());
        Assert.assertFalse(rendererRollbackOutput.getFailedToRollback().isEmpty());
        Assert.assertTrue(rendererRollbackOutput.getFailedToRollback().get(0).getInterface().isEmpty());
        Mockito.verify(this.crossConnect, Mockito.times(0)).deleteCrossConnect(Mockito.anyString(),
            Mockito.anyString());
        Mockito.verify(this.openRoadmInterfaces, Mockito.times(3)).deleteInterface(Mockito.eq("node1"),
            Mockito.anyString());
    }

    @Test
    public void testRollbackInterfacesException() throws OpenRoadmInterfaceException {
        setMountPoint(new MountPointStub(getDataBroker()));

        NodeInterfaceBuilder nodeInterfaceBuilder = new NodeInterfaceBuilder();
        nodeInterfaceBuilder.setNodeId("node1");
        nodeInterfaceBuilder.withKey(new NodeInterfaceKey("node1"));
        nodeInterfaceBuilder.setConnectionId(new ArrayList<>());
        List<String> ethInterfacesId = new ArrayList<>();
        ethInterfacesId.add("node1-" + StringConstants.CLIENT_TOKEN + "-ETHERNET");
        nodeInterfaceBuilder.setEthInterfaceId(ethInterfacesId);
        List<NodeInterface> nodeInterfaces = new ArrayList<>();
        nodeInterfaces.add(nodeInterfaceBuilder.build());
        RendererRollbackInputBuilder rendererRollbackInputBuilder = new RendererRollbackInputBuilder();
        rendererRollbackInputBuilder.setNodeInterface(nodeInterfaces);

        Mockito.doThrow(OpenRoadmInterfaceException.class).when(this.openRoadmInterfaces)
            .deleteInterface(Mockito.eq("node1"), Mockito.anyString());
        RendererRollbackOutput rendererRollbackOutput =
            this.deviceRendererService.rendererRollback(rendererRollbackInputBuilder.build());
        Assert.assertFalse(rendererRollbackOutput.isSuccess());
        Assert.assertEquals(1,rendererRollbackOutput.getFailedToRollback().size());
        Assert.assertEquals(1, rendererRollbackOutput.getFailedToRollback().get(0).getInterface().size());
        Assert.assertEquals(ethInterfacesId.get(0), rendererRollbackOutput.getFailedToRollback().get(0)
            .getInterface().get(0));
        Mockito.verify(this.crossConnect, Mockito.times(0))
            .deleteCrossConnect(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(this.openRoadmInterfaces, Mockito.times(1))
            .deleteInterface("node1",ethInterfacesId.get(0));
    }
}
