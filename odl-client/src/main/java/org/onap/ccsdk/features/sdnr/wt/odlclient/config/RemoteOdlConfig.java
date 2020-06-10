/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.config;

public class RemoteOdlConfig {

    private static final String FILENAME = "etc/remoteodl.properties";

    private final String baseUrl;
    private final String wsUrl;
    private final AuthMethod authMethod;
    private final String username;
    private final String password;

    private final boolean enabled;

    public RemoteOdlConfig() {
        this.baseUrl = "http://sdnr:8181";
        this.wsUrl = "ws://sdnr:8181/websocket";
        this.authMethod = AuthMethod.BASIC;
        this.username = "admin";
        this.password = "Kp8bJ4SXszM0WXlhak3eHlcse2gAw84vaoGGmJvUy2U";
        this.enabled = true;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getWebsocketUrl() {
        return this.wsUrl;
    }

    public AuthMethod getAuthenticationMethod() {
        return this.authMethod;
    }

    public String getCredentialUsername() {
        return this.username;
    }

    public String getCredentialPassword() {
        return this.password;
    }

    public enum AuthMethod {
        BASIC, TOKEN
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
