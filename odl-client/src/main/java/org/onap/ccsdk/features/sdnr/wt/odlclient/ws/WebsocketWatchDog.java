/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.ws;

import java.io.IOException;
import java.util.Date;
import org.eclipse.jetty.websocket.api.Session;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.SdnrNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebsocketWatchDog implements SdnrWebsocketCallback {

    private static final long MAX_RETRIES = 5;
    private static final Logger LOG = LoggerFactory.getLogger(WebsocketWatchDog.class);

    private static final int DELAY_BETWEEN_CONNECTIONTRIALS = 5000;

    private final TriggeredRunner runner = new TriggeredRunner() {

        private long timeToConnect = new Date().getTime();
        private boolean triggered = false;

        @Override
        public void run() {

            while (!closed) {
                if (!isConnected() && triggered) {
                    triggered = false;
                    LOG.info("tick");
                    final long now = new Date().getTime();
                    if (now > timeToConnect) {
                        try {
                            reconnect();
                        } catch (Exception e) {
                            LOG.warn("problem to establish ws connection: ", e);
                            decrementAndTriggerDelayed();
                        }
                    }
                }
                trySleep(1000);
            }

        }

        private void trySleep(int sleepMs) {
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        void trigger(int delay) {
            this.timeToConnect = new Date().getTime() + delay;
            triggered = true;
        }

    };

    abstract void reconnect() throws IOException, Exception;

    protected boolean isConnected() {
        return this.session == null ? false : this.session.isOpen();
    }

//    private long lastConnect;
    private long retries = MAX_RETRIES;

    private final SdnrWebsocketCallback remoteCallback;
    private final Thread watcherThread;
    private Session session;
    private boolean closed;

    public WebsocketWatchDog(SdnrWebsocketCallback callback) {
        this.remoteCallback = callback;
        this.watcherThread = new Thread(this.runner);
    }

    private void decrementAndTriggerDelayed() {
        if (!this.closed) {
            if (this.retries > 0) {
                this.retries--;
            }
            if (this.retries > 0) {
                this.runner.trigger(DELAY_BETWEEN_CONNECTIONTRIALS);
            }
        }
    }

    @Override
    public void onMessageReceived(String msg) {
        this.remoteCallback.onMessageReceived(msg);
    }

    @Override
    public void onDisconnect(int statusCode, String reason) {
        this.session = null;
        this.remoteCallback.onDisconnect(statusCode, reason);
        this.decrementAndTriggerDelayed();
    }

    @Override
    public void onError(Throwable cause) {
        this.remoteCallback.onError(cause);
    }

    @Override
    public void onConnect(Session lsession) {
        this.session = lsession;
        // reset connection retries
        this.retries = MAX_RETRIES;
//        this.lastConnect = new Date().getTime();
        this.remoteCallback.onConnect(lsession);

    }

    @Override
    public void onNotificationReceived(SdnrNotification notification) {
        this.remoteCallback.onNotificationReceived(notification);

    }

    public void stop() {
        try {
            this.closed = true;
            if (this.session != null) {
                this.session.close();
            }
            this.watcherThread.join();

        } catch (Exception e) {
            LOG.warn("problem closing watcher thread: ", e);
        }
    }

    public boolean isStopped() {
        return this.watcherThread == null ? true : !this.watcherThread.isAlive();
    }

    public void start() {
        this.watcherThread.start();
        this.runner.trigger();

    }

    private abstract class TriggeredRunner implements Runnable {

        void trigger() {
            this.trigger(0);
        }

        abstract void trigger(int delay);
    }

}
