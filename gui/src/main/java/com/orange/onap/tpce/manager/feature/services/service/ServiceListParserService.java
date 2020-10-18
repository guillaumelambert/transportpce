package com.orange.onap.tpce.manager.feature.services.service;

import com.orange.onap.tpce.manager.feature.services.domain.LinkPoint;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceList;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceListBody;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.Node;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePath;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;
import com.orange.onap.tpce.manager.common.util.rest.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceListParserService {
    private RestTemplateService restTemplateService;

    @Autowired
    public ServiceListParserService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;

    }

    public ServiceListBody fetchService() {
        return this.restTemplateService.fetchService();
    }

    public ServiceList getServicePathList() {
        ServicePathList servicePathList = this.restTemplateService.getServicePathContent();
        return mapServicePathToService(servicePathList);
    }

    private ServiceList mapServicePathToService(ServicePathList servicePathList) {
        ServiceList serviceList = new ServiceList();
        serviceList.setServices(new ArrayList<>());
        if (servicePathList != null && servicePathList.getServicePathContent() != null) {
            for (ServicePath servicePath :
                    servicePathList.getServicePathContent().getServicePathList()) {
                com.orange.onap.tpce.manager.feature.services.domain.Service service = new com.orange.onap.tpce.manager.feature.services.domain.Service();
                service.setServiceName(servicePath.getServicePathName());
                servicePath.getPathDescription().getAToZDirection().getNodeList().sort((node1, node2) -> {
                    Integer firstValue = Integer.parseInt(node1.getId());
                    Integer secondValue = Integer.parseInt(node2.getId());
                    return firstValue.compareTo(secondValue);
                });
                Node firstNode = servicePath.getPathDescription().getAToZDirection().getNodeList().get(0);
                Node lastNode = servicePath.getPathDescription().getAToZDirection().getNodeList()
                        .get(servicePath.getPathDescription().getAToZDirection().getNodeList().size() - 1);

                LinkPoint firstLinkPoint = new LinkPoint();
                firstLinkPoint.setNodeId(firstNode.getResource().getTpNodeId());
                firstLinkPoint.setServiceFormat(servicePath.getServiceZEnd().getServiceFormat());
                firstLinkPoint.setServiceRate(servicePath.getServiceZEnd().getServiceRate());

                LinkPoint secondLinkPoint = new LinkPoint();
                secondLinkPoint.setNodeId(lastNode.getResource().getTpNodeId());
                secondLinkPoint.setServiceFormat(servicePath.getServiceZEnd().getServiceFormat());
                secondLinkPoint.setServiceRate(servicePath.getServiceZEnd().getServiceRate());

                service.setSourcePoint(firstLinkPoint);
                service.setTargetPoint(secondLinkPoint);

                serviceList.getServices().add(service);

            }

        }
        return serviceList;
    }
}
