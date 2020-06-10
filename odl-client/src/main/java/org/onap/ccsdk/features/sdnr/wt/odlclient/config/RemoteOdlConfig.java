/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteOdlConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteOdlConfig.class);
    private static final String FILENAME = "etc/remoteodl.properties";
    public static final String KEY_BASEURL = "baseurl";
    public static final String KEY_WSURL = "wsurl";
    public static final String KEY_AUTHMETHOD = "auth";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ENABLED = "enabled";
    public static final String KEY_TRUSTALL = "trustall";

    private static final String DEFAULT_BASEURL = "http://sdnr:8181";
    private static final String DEFAULT_WSURL = "ws://sdnr:8181/websocket";
    private static final String DEFAULT_AUTHMETHOD = AuthMethod.BASIC.name();
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "Kp8bJ4SXszM0WXlhak3eHlcse2gAw84vaoGGmJvUy2U";
    private static final boolean DEFAULT_ENABLED = true;
    private static final boolean DEFAULT_TRUSTALL = false;

    private final String baseUrl;
    private final String wsUrl;
    private final AuthMethod authMethod;
    private final String username;
    private final String password;
    private final boolean trustall;
    private final boolean enabled;

    public RemoteOdlConfig() {
        this(FILENAME);
    }

    public RemoteOdlConfig(String filename) {
        // try to load
        File file = new File(filename);
        Properties prop = null;
        if (file.exists() && file.isFile() && file.canRead()) {
            try (InputStream input = new FileInputStream(file)) {
                prop = new Properties();
                prop.load(input);

            } catch (IOException ex) {
                LOG.warn("problem loading config file {}: ", filename, ex);
            }
        }
        if (prop != null) {
            this.baseUrl = prop.getProperty(KEY_BASEURL, DEFAULT_BASEURL);
            this.wsUrl = prop.getProperty(KEY_WSURL, DEFAULT_WSURL);
            this.authMethod = AuthMethod.valueOf(prop.getProperty(KEY_AUTHMETHOD, DEFAULT_AUTHMETHOD));
            this.username = prop.getProperty(KEY_USERNAME, DEFAULT_USERNAME);
            this.password = prop.getProperty(KEY_PASSWORD, DEFAULT_PASSWORD);
            this.enabled = "true".equals(prop.getProperty(KEY_ENABLED, String.valueOf(DEFAULT_ENABLED)));
            this.trustall = "true"
                    .equals(prop.getProperty(KEY_TRUSTALL, String.valueOf(DEFAULT_TRUSTALL)));
        } else {
            this.baseUrl = DEFAULT_BASEURL;
            this.wsUrl = DEFAULT_WSURL;
            this.authMethod = AuthMethod.BASIC;
            this.username = DEFAULT_USERNAME;
            this.password = DEFAULT_PASSWORD;
            this.enabled = DEFAULT_ENABLED;
            this.trustall = DEFAULT_TRUSTALL;
        }
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

    public boolean trustAllCerts() {
        return this.trustall;
    }
}
