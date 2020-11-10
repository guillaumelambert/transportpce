/*
 * Copyright © 2020 Orange Labs, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.transportpce.pce;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
<<<<<<< HEAD
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.transportpce.common.DataStoreContext;
=======
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.opendaylight.mdsal.binding.dom.codec.spi.BindingDOMCodecServices;
>>>>>>> standalone/stable/aluminium
import org.opendaylight.transportpce.common.network.NetworkTransactionImpl;
import org.opendaylight.transportpce.common.network.RequestProcessor;
import org.opendaylight.transportpce.pce.gnpy.ConnectToGnpyServer;
import org.opendaylight.transportpce.pce.gnpy.JerseyServer;
import org.opendaylight.transportpce.pce.utils.PceTestData;
import org.opendaylight.transportpce.pce.utils.PceTestUtils;
import org.opendaylight.transportpce.test.AbstractTest;
<<<<<<< HEAD


//@RunWith(MockitoJUnitRunner.class)
=======
import org.opendaylight.yangtools.yang.model.parser.api.YangParserFactory;

@RunWith(MockitoJUnitRunner.class)
>>>>>>> standalone/stable/aluminium
public class PceSendingPceRPCsTest extends AbstractTest {

    private PceSendingPceRPCs pceSendingPceRPCs;
    private NetworkTransactionImpl networkTransaction;
<<<<<<< HEAD
    private DataStoreContext dataStoreContext = this.getDataStoreContextUtil();
    private DataBroker dataBroker = this.getDataBroker();
=======
    @Mock
    private YangParserFactory yangParserFactory;
    @Mock
    private BindingDOMCodecServices bindingDOMCodecServices;
>>>>>>> standalone/stable/aluminium
    private JerseyServer jerseyServer = new JerseyServer();


    @Before
    public void setUp() {
        networkTransaction = new NetworkTransactionImpl(new RequestProcessor(this.getDataBroker()));
        PceTestUtils.writeNetworkInDataStore(this.getDataBroker());
<<<<<<< HEAD
        pceSendingPceRPCs =
                new PceSendingPceRPCs(PceTestData.getPCE_test1_request_54(), networkTransaction);
=======
        pceSendingPceRPCs = new PceSendingPceRPCs(PceTestData.getPCE_test1_request_54(),
                        networkTransaction, bindingDOMCodecServices);
>>>>>>> standalone/stable/aluminium
    }

    @Test
    public void cancelResourceReserve() {
        pceSendingPceRPCs.cancelResourceReserve();
        Assert.assertTrue("Success should equal to true", pceSendingPceRPCs.getSuccess());
    }

    @Test
    public void pathComputationTest() throws Exception {
        jerseyServer.setUp();
        pceSendingPceRPCs =
                new PceSendingPceRPCs(PceTestData.getGnpyPCERequest("XPONDER-1", "XPONDER-2"),
<<<<<<< HEAD
                        networkTransaction);
=======
                        networkTransaction, null);
>>>>>>> standalone/stable/aluminium

        pceSendingPceRPCs.pathComputation();
        ConnectToGnpyServer connectToGnpy = new ConnectToGnpyServer();
        Assert.assertTrue(connectToGnpy.isGnpyURLExist());
        jerseyServer.tearDown();

    }

    @Test
    public void checkMessage() {
        Assert.assertNull(pceSendingPceRPCs.getMessage());
    }

    @Test
    public void responseCodeTest() {
        Assert.assertNull(pceSendingPceRPCs.getResponseCode());
    }

    @Test
    public void gnpyAtoZ() {
        Assert.assertNull(pceSendingPceRPCs.getGnpyAtoZ());
    }

    @Test
    public void getGnpyZtoA() {
        Assert.assertNull(pceSendingPceRPCs.getGnpyZtoA());
    }


}
