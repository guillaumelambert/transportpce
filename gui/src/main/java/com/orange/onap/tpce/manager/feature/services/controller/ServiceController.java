package com.orange.onap.tpce.manager.feature.services.controller;

import com.orange.onap.tpce.manager.feature.services.domain.ServiceList;
import com.orange.onap.tpce.manager.feature.services.service.ServiceListParserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
@Log4j2
public class ServiceController {

    private ServiceListParserService serviceListParserService;

    @Autowired
    public ServiceController(ServiceListParserService serviceListParserService) {
        this.serviceListParserService = serviceListParserService;
    }
    // TODO update prefix duplicate mapping

    @RequestMapping(value = {"/api/service"}, method = RequestMethod.GET)
    public ResponseEntity<ServiceList> getService() {
        ServiceList serviceList = serviceListParserService.fetchService().getServiceList();
        return ResponseEntity.ok(serviceList);
    }

    @RequestMapping(value = {"/api/servicePaths"}, method = RequestMethod.GET)
    public ResponseEntity<ServiceList> getServiceFromServicePath(HttpServletRequest request) {
        return ResponseEntity.ok(serviceListParserService.getServicePathList());
    }
}
