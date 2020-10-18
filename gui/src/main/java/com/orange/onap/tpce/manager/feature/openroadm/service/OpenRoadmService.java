package com.orange.onap.tpce.manager.feature.openroadm.service;


import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.ParsedNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.RawNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.topology.service.TopologyService;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.type.clli.ClliNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.visual.service.VisualMapperService;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.WebVowl;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;
import com.orange.onap.tpce.manager.common.util.rest.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenRoadmService {

    private RestTemplateService restTemplateService;
    private TopologyService topologyService;
    private VisualMapperService visualMapperService;

    @Autowired
    public OpenRoadmService(RestTemplateService restTemplateService,
                            TopologyService topologyService,
                            VisualMapperService visualMapperService) {
        this.restTemplateService = restTemplateService;
        this.visualMapperService = visualMapperService;
        this.topologyService = topologyService;
    }

    public WebVowl fetchAndMapNetworks() throws IOException {

        RawNetwork rawNetwork = restTemplateService.fetchTopology();
        ParsedNetwork parsedNetwork = topologyService.parseNetworks(rawNetwork);
        ClliNetwork clliNetwork = topologyService.integrateNetworks(parsedNetwork);

        return visualMapperService.mapToWebVowl(clliNetwork, parsedNetwork);
    }

    public RawNetwork fetchNetworks() {

        return restTemplateService.fetchTopology();

    }

    public ServicePathList getServicePathContent() {
        return restTemplateService.getServicePathContent();
    }

}
