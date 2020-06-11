/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.notifications;

import javax.xml.bind.annotation.XmlRootElement;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.SdnrNotification;

@XmlRootElement(name = "ObjectCreationNotification")
public class ObjectCreationNotification extends BaseNotification implements SdnrNotification {

    @Override
    public String toString() {
        return "ObjectCreationNotification [getNodeName()=" + getNodeName() + ", getCounter()="
                + getCounter() + ", getTimeStamp()=" + getTimeStamp() + ", getObjectId()="
                + getObjectId() + ", getType()=" + getType() + "]";
    }

}
