/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.ws;

import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class SdnrWebsocketClient extends WebsocketWatchDog {

    private final URI url;
    private WebSocketClient wsClient;

    public SdnrWebsocketClient(String url, SdnrWebsocketCallback callback) throws URISyntaxException {
        super(callback);
        this.url = new URI(url);
    }

    @Override
    void reconnect() throws Exception {
        this.wsClient = new WebSocketClient();
        this.wsClient.start();
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        this.wsClient.connect(new SdnrWebsocket(this), this.url, request);
    }






}
