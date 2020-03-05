package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl;
import java.util.List;

import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.AlarmNotification;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.OrgOpenroadmAlarmListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.alarm.ResourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.alarms.rev190911.alarms.AlarmInventory;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.alarms.rev190911.alarms.alarm.list.AlarmBuilder;;

public class OscaFaultNotificationListener implements OrgOpenroadmAlarmListener {
    private static final Logger log = LoggerFactory.getLogger(OrgOpenroadmAlarmListener.class);
    

	@Override
	public void onAlarmNotification(AlarmNotification notification) {
		log.info("AlarmNotification {}",notification);
		notification.getRaiseTime();
		

		
	}


}
	


