/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.transportpce.common;

import org.onap.ccsdk.features.sdnr.wt.odlclient.config.RemoteOdlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Globals {
    private Globals() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(Globals.class);
    private static RemoteOdlConfig config = new RemoteOdlConfig();
    private static String ENV_SLEEP_10000 = config.getEnvSleep_10000();
    private static String ENV_SLEEP_1000 = config.getEnvSleep_1000();
    private static String ENV_SLEEP_3000 = config.getEnvSleep_3000();
    private static String ENV_SLEEP_100 = config.getEnvSleep_100();
    private static String ENV_SLEEP_500 = config.getEnvSleep_500();
    private static String ENV_SLEEP_200 = config.getEnvSleep_200();
    private static String ENV_SLEEP_90000 = config.getEnvSleep_90000();
    public static int SLEEP_10000 = getEnvOrDefault(ENV_SLEEP_10000, 10000);
    public static int SLEEP_1000 = getEnvOrDefault(ENV_SLEEP_1000, 1000);
    public static int SLEEP_3000 = getEnvOrDefault(ENV_SLEEP_3000, 3000);
    public static int SLEEP_100 = getEnvOrDefault(ENV_SLEEP_100, 100);
    public static int SLEEP_500 = getEnvOrDefault(ENV_SLEEP_500, 500);
    public static int SLEEP_200 = getEnvOrDefault(ENV_SLEEP_200, 200);
    public static int SLEEP_90000 = getEnvOrDefault(ENV_SLEEP_90000, 90000);

    private static int getEnvOrDefault(String envVar, int defaultValue) {
        int value;
        if (envVar != null) {
            try {
                value = Integer.parseInt(envVar);
            } catch (NumberFormatException e) {
                value = 0;
            }
        } else {
            value = defaultValue;
        }
        LOG.info("The sleep timer is set to:" + value);
        return value;
    }
}