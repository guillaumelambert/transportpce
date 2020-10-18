package com.orange.onap.tpce.manager.feature.openroadm.controller;

import com.orange.onap.tpce.manager.feature.openroadm.service.OpenRoadmService;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.RawNetwork;
import com.orange.onap.tpce.manager.feature.openroadm.visual.domain.WebVowl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
@Log4j2
public class OpenRoadmController {

    private OpenRoadmService openRoadmService;

    @Autowired
    public OpenRoadmController(OpenRoadmService openRoadmService) {
        this.openRoadmService = openRoadmService;
    }

    @RequestMapping(value = {"api/topology"}, method = RequestMethod.GET)
    public WebVowl getTopology() {
        log.debug("getting Mapped Topology...");

        WebVowl webVowl = null;
        try {
            webVowl = openRoadmService.fetchAndMapNetworks();
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return webVowl;
    }

    @RequestMapping(value = {"api/networks"}, method = RequestMethod.GET)
    public RawNetwork getRawNetworks() {
        log.debug("getting RoadM...");
        return openRoadmService.fetchNetworks();
    }
}
