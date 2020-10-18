package com.orange.onap.tpce.manager.feature.openroadm.topology.domain;

import lombok.Getter;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.Network;
import java.util.List;

@Getter
public class RawNetwork {

    private List<Network> network;
}

