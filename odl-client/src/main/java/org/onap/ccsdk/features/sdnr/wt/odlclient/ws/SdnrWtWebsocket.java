/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.ws;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebSocket(maxTextMessageSize = 128 * 1024)
public class SdnrWtWebsocket {

    private static final Logger LOG = LoggerFactory.getLogger(SdnrWtWebsocket.class);

    @SuppressWarnings("unused")
    private Session session;

    private final SdnrWebsocketCallback callback;

    public SdnrWtWebsocket(SdnrWebsocketCallback cb) {
        this.callback = cb;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        LOG.debug("Connection closed: {} - {}", statusCode, reason);
        this.session = null;
    }

    @OnWebSocketConnect
    public void onConnect(Session lsession) {
        LOG.debug("Got connect: {}", lsession);
        this.session = lsession;

    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        LOG.debug("Got msg: {}", msg);
        this.callback.onMessageReceived(msg);
    }

    @OnWebSocketError
    public void onError(Throwable cause) {
        LOG.warn("WebSocket Error: {}", cause);
        this.callback.onError(cause);
    }
}