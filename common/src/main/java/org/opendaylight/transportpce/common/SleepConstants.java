/*
 * Copyright (C) 2021 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.transportpce.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SleepConstants {

    private static final Logger LOG = LoggerFactory.getLogger(SleepConstants.class);

    public static final String ENVVAR_SIMULATORMODE = "TRANSPORTPCE_SIMULATOR_MODE";

    public static final long PCE_DELAY_10000 = isSimMode() ? 9999 : 10000;
    public static final long OLM_OLM_TIMER_1 = isSimMode() ? 10 : 120000;
    public static final long OLM_OLM_TIMER_2 = isSimMode() ? 10 : 20000;
    public static final long OLM_DELAY_90000 = isSimMode() ? 10 : 90000;
    public static final long RENDERER_DELAY_3000 = isSimMode() ? 10 : 3000;
    public static final long RENDERER_DELAY_10000 = isSimMode() ? 10 : 10000;
    public static final long RENDERER_SERVICE_ACTIVATION_TEST_RETRY_TIME = isSimMode() ? 10 : 10000;


    public static boolean isSimMode() {
        String value = null;
        try {
            value = System.getenv(ENVVAR_SIMULATORMODE);
        } catch (SecurityException e) {
            LOG.warn("unable to load env:", e);
        }
        return value != null && value.equals("true");
    }

    private SleepConstants() {
    }
}
