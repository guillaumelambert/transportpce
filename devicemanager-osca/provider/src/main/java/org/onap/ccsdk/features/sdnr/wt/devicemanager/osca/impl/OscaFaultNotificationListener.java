package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl;

import java.util.List;

import org.onap.ccsdk.features.sdnr.wt.dataprovider.model.DataProvider;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.types.FaultData;
import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.NetconfAccessor;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.ActiveAlarmList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.AlarmNotification;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.OrgOpenroadmAlarmListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.Severity;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.active.alarm.list.ActiveAlarms;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.alarm.ResourceBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.Faultlog;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.FaultlogBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.FaultlogEntity;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.SeverityType;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OscaFaultNotificationListener implements OrgOpenroadmAlarmListener {
	private static final Logger log = LoggerFactory.getLogger(OrgOpenroadmAlarmListener.class);
	private final NetconfAccessor accesor;
	private final DataProvider databaseProvider;
	private Integer sequenceNumber = 0;

	public OscaFaultNotificationListener(NetconfAccessor netConfAccessor, DataProvider databaseProvider) {
		this.databaseProvider = databaseProvider;
		this.accesor = netConfAccessor;
	}

	@Override
	public void onAlarmNotification(AlarmNotification notification) {

		log.info("AlarmNotification {} \t {}", notification.getId(), notification.getAdditionalDetail());

		FaultlogEntity faultAlarm = new FaultlogBuilder().setObjectId(notification.getCircuitId())
				.setProblem(notification.getProbableCause().getCause().getName())
				.setTimestamp(notification.getRaiseTime()).setId(notification.getId())
				.setNodeId(notification.getResource().getDevice().getNodeId().getValue())
				.setSeverity(checkSeverityValue(notification.getSeverity())).build();

		databaseProvider.writeFaultLog(faultAlarm);

	}

	// Read Alarm Data

	private ActiveAlarmList getActiveAlarmList(NetconfAccessor accessor) {
		final Class<ActiveAlarmList> classAlarm = ActiveAlarmList.class;
		log.info("Get PM data for element {}", accessor.getNodeId().getValue());
		InstanceIdentifier<ActiveAlarmList> alarmDataIid = InstanceIdentifier.builder(classAlarm).build();

		ActiveAlarmList alarmData = accessor.getTransactionUtils().readData(accessor.getDataBroker(),
				LogicalDatastoreType.OPERATIONAL, alarmDataIid);

		return alarmData;
	}

// Mapping the alarm data with the fault data
	protected FaultData writeFaultData(Integer sequenceNumber) {
		FaultData faultData = new FaultData();

		List<ActiveAlarms> activeAlarms = this.getActiveAlarmList(this.accesor).getActiveAlarms();
		for (ActiveAlarms activeAlarm : activeAlarms) {
			faultData.add(this.accesor.getNodeId(), sequenceNumber, activeAlarm.getRaiseTime(),
					activeAlarm.getResource().getDevice().getNodeId().getValue(),
					activeAlarm.getProbableCause().getCause().getName(), checkSeverityValue(activeAlarm.getSeverity()));

		
		}

		return faultData;

	}

// Write into the FaultLog
	protected void writeFaultLog(FaultData faultData) {
		List<Faultlog> faultLog = faultData.getProblemList();
		for (Faultlog fe : faultLog) {
			this.databaseProvider.writeFaultLog(fe);
		}
	}

	// Mapping Severity of AlarmNotification to SeverityType of FaultLog
	private SeverityType checkSeverityValue(Severity severity) {
		SeverityType severityType = null;
		log.info("Device Severity: {}", severity.getName());

		switch (severity.getName()) {
		case ("warning"):
			severityType = SeverityType.Warning;
			break;
		case ("major"):
			severityType = SeverityType.Major;
			break;
		case ("minor"):
			severityType = SeverityType.Minor;
			break;
		case ("clear"):
			severityType = SeverityType.NonAlarmed;
			break;
		case ("critical"):
			severityType = SeverityType.Critical;
			break;
		case ("indeterminate"):
			severityType = SeverityType.Critical;
			break;
		}
		return severityType;

	}

}
