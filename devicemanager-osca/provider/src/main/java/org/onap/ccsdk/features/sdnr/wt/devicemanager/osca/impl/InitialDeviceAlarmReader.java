package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl;
import java.util.List;
import org.eclipse.jdt.annotation.NonNull;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.DeviceManagerServiceProvider;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.service.FaultService;
import org.onap.ccsdk.features.sdnr.wt.devicemanager.types.FaultData;
import org.onap.ccsdk.features.sdnr.wt.netconfnodestateservice.NetconfAccessor;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.ActiveAlarmList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.OrgOpenroadmAlarmListener;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.Severity;
import org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.active.alarm.list.ActiveAlarms;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.data.provider.rev190801.SeverityType;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitialDeviceAlarmReader {
	private static final Logger log = LoggerFactory.getLogger(OrgOpenroadmAlarmListener.class);
	private final NetconfAccessor netConfAccesor;
	private final @NonNull FaultService faultEventListener;
	private Integer count = 1;
	
	public InitialDeviceAlarmReader(NetconfAccessor accessor, DeviceManagerServiceProvider serviceProvider) {
		this.netConfAccesor = accessor;
		this.faultEventListener= serviceProvider.getFaultService();
	}

	// Read Alarm Data

	private ActiveAlarmList getActiveAlarmList(NetconfAccessor accessor) {
		final Class<ActiveAlarmList> classAlarm = ActiveAlarmList.class;
		log.info("Get Alarm data for element {}", accessor.getNodeId().getValue());
		InstanceIdentifier<ActiveAlarmList> alarmDataIid = InstanceIdentifier.builder(classAlarm).build();

		ActiveAlarmList alarmData = accessor.getTransactionUtils().readData(accessor.getDataBroker(),
				LogicalDatastoreType.OPERATIONAL, alarmDataIid);
	
		log.info("AlarmData {}", alarmData.toString());
		return alarmData;
	}

// Mapping the alarm data with the fault data
	protected FaultData writeFaultData() {
		FaultData faultData = new FaultData();
		if (this.getActiveAlarmList(this.netConfAccesor).getActiveAlarms() != null) {
			List<ActiveAlarms> activeAlarms = this.getActiveAlarmList(this.netConfAccesor).getActiveAlarms();
			if (!activeAlarms.isEmpty()) {
				for (ActiveAlarms activeAlarm : activeAlarms) {
					faultData.add(this.netConfAccesor.getNodeId(), this.count, activeAlarm.getRaiseTime(),
							activeAlarm.getResource().getDevice().getNodeId().getValue(),
							activeAlarm.getProbableCause().getCause().getName(),
							checkSeverityValue(activeAlarm.getSeverity()));
					count = count + 1;
				}
				return faultData;
			}
		}
		return faultData;
	}

// Write into the FaultLog
//	protected void writeAlarmLog(FaultData faultData) {
//		if (faultData != null) {
//			List<Faultlog> faultLog = faultData.getProblemList();
//			for (Faultlog fe : faultLog) {
//				this.databaseProvider.writeFaultLog(fe);
//			}
//		}
//
//	}
// Use the FaultService for Alarm notifications
	protected void faultService() {
		this.faultEventListener.initCurrentProblemStatus(this.netConfAccesor.getNodeId(), writeFaultData());
//		writeAlarmLog(writeFaultData(count));
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
