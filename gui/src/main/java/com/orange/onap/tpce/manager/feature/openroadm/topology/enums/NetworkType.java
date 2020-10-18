package com.orange.onap.tpce.manager.feature.openroadm.topology.enums;

/**
 * @author Shaaban Ebrahim
 */
public enum NetworkType {

    OpenRoadmTopology("openroadm-topology"), ClliNetwork("clli-network"), OpenroadmNetwork("openroadm-network"),

    OtnNetwork("otn-topology");

    private final String networkValue;

    NetworkType(String networkValue) {
        this.networkValue = networkValue;
    }

    public String getNetworkValue() {
        return networkValue;
    }
}
