/*
 * Copyright © 2016 AT&T and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.transportpce.networkmodel.dto;

import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev181019.OrgOpenroadmAlarmListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.de.operations.rev181019.OrgOpenroadmDeOperationsListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.OrgOpenroadmDeviceListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.OrgOpenroadmLldpListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.tca.rev181019.OrgOpenroadmTcaListener;
import org.opendaylight.yangtools.concepts.ListenerRegistration;

public class NodeRegistration22 {

    private final String nodeId;
    private final ListenerRegistration<OrgOpenroadmAlarmListener> accessAlarmNotificationListenerRegistration;
    private final ListenerRegistration<OrgOpenroadmDeOperationsListener>
        accessDeOperationasNotificationListenerRegistration;
    private final ListenerRegistration<OrgOpenroadmDeviceListener> accessDeviceNotificationListenerRegistration;
    private final ListenerRegistration<OrgOpenroadmLldpListener> accessLldpNotificationListenerRegistration;
    private final ListenerRegistration<OrgOpenroadmTcaListener> accessTcaNotificationListenerRegistration;

    public NodeRegistration22(String nodeId,
                              ListenerRegistration<OrgOpenroadmAlarmListener>
                                  accessAlarmNotificationListenerRegistration,
                              ListenerRegistration<OrgOpenroadmDeOperationsListener>
                                  accessDeOperationasNotificationListenerRegistration,
                              ListenerRegistration<OrgOpenroadmDeviceListener>
                                  accessDeviceNotificationListenerRegistration,
                              ListenerRegistration<OrgOpenroadmLldpListener>
                                  accessLldpNotificationListenerRegistration,
                              ListenerRegistration<OrgOpenroadmTcaListener>
                                  accessTcaNotificationListenerRegistration) {
        this.nodeId = nodeId;
        this.accessAlarmNotificationListenerRegistration = accessAlarmNotificationListenerRegistration;
        this.accessDeOperationasNotificationListenerRegistration = accessDeOperationasNotificationListenerRegistration;
        this.accessDeviceNotificationListenerRegistration = accessDeviceNotificationListenerRegistration;
        this.accessLldpNotificationListenerRegistration = accessLldpNotificationListenerRegistration;
        this.accessTcaNotificationListenerRegistration = accessTcaNotificationListenerRegistration;
    }

    public String getNodeId() {
        return nodeId;
    }

    public ListenerRegistration<OrgOpenroadmAlarmListener> getAccessAlarmNotificationListenerRegistration() {
        return accessAlarmNotificationListenerRegistration;
    }

    public ListenerRegistration<OrgOpenroadmDeOperationsListener>
        getAccessDeOperationasNotificationListenerRegistration() {
        return accessDeOperationasNotificationListenerRegistration;
    }

    public ListenerRegistration<OrgOpenroadmDeviceListener> getAccessDeviceNotificationListenerRegistration() {
        return accessDeviceNotificationListenerRegistration;
    }

    public ListenerRegistration<OrgOpenroadmLldpListener> getAccessLldpNotificationListenerRegistration() {
        return accessLldpNotificationListenerRegistration;
    }

    public ListenerRegistration<OrgOpenroadmTcaListener> getAccessTcaNotificationListenerRegistration() {
        return accessTcaNotificationListenerRegistration;
    }

}
