package org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.test;

import org.junit.Test;

/*
 * import org.onap.ccsdk.features.sdnr.wt.dataprovider.model.DataProvider;
 * import org.onap.ccsdk.features.sdnr.wt.devicemanager.osca.impl.
 * OscaFaultNotificationListener; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.
 * AlarmNotification; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.alarm.
 * ProbableCause; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.alarm.
 * ProbableCauseBuilder; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.alarm.
 * Resource; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.alarm.
 * ResourceBuilder; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.common.node.types.rev191129.
 * NodeIdType; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.probablecause.rev191129.
 * ProbableCauseEnum; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.resource.rev191129.resource.
 * Device; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.resource.rev191129.resource.
 * DeviceBuilder; import
 * org.opendaylight.yang.gen.v1.http.org.openroadm.alarm.rev191129.Severity;
 * import
 * org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.
 * rev130715.DateAndTime;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.mockito.Mockito.*;
 * 
 * 
 * public class TestNotification { private static final String myCircuitId =
 * "Test_Id"; private static final String myId = "Alarm Id"; DateAndTime
 * myRaiseTime = new DateAndTime("2020-02-25T10:08:06.7Z"); ProbableCause
 * myProbableCause = new
 * ProbableCauseBuilder().setCause(ProbableCauseEnum.AutomaticLaserShutdown).
 * build(); Device device = new
 * DeviceBuilder().setNodeId(NodeIdType.getDefaultInstance("zNhe2i5")).build();
 * Resource myResource = new ResourceBuilder().setDevice(device).build();
 * DataProvider dataProvider; Severity severity;
 * 
 * private static final Logger LOG =
 * LoggerFactory.getLogger(TestNotification.class);
 * 
 * @Test public void testNotification() { // instantiate the listener
 * 
 * dataProvider = mock(DataProvider.class); severity = Severity.Critical;
 * OscaFaultNotificationListener alarmListener = new
 * OscaFaultNotificationListener(dataProvider); AlarmNotification notification =
 * mock(AlarmNotification.class);
 * 
 * when(notification.getCircuitId()).thenReturn(myCircuitId);
 * when(notification.getRaiseTime()).thenReturn(myRaiseTime);
 * when(notification.getProbableCause()).thenReturn(myProbableCause);
 * when(notification.getResource()).thenReturn(myResource);
 * when(notification.getId()).thenReturn(myId);
 * when(notification.getSeverity()).thenReturn(severity);
 * 
 * alarmListener.onAlarmNotification(notification);
 * 
 * assertEquals(notification.getCircuitId(), myCircuitId);
 * assertEquals(notification.getRaiseTime(), myRaiseTime);
 * assertEquals(notification.getProbableCause(), myProbableCause);
 * assertEquals(notification.getResource(), myResource);
 * 
 * LOG.info(notification.toString());
 * 
 * }
 * 
 * }
 */
