package com.orange.onap.tpce.manager.common.util.rest;

import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.RawNetwork;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceListBody;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;


public interface RestTemplateService {

    RawNetwork fetchTopology();

    ServiceListBody fetchService();

    ServicePathList getServicePathContent();
}
