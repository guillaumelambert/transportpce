package com.orange.onap.tpce.manager.feature.openroadm.topology.domain;

import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.device.DeviceNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.otn.OtnNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.roadm.RoadMNetwork;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParsedNetwork {

    private ClliNetwork clliNetwork;
    private RoadMNetwork roadMNetwork;
    private DeviceNetwork deviceNetwork;
    private OtnNetwork otnNetwork;

}
