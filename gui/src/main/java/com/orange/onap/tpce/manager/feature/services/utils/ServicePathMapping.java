package com.orange.onap.tpce.manager.feature.services.utils;

import com.orange.onap.tpce.manager.feature.services.domain.LinkPoint;
import com.orange.onap.tpce.manager.feature.services.domain.Service;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceList;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.Node;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePath;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;
import com.orange.onap.tpce.manager.common.util.mapping.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ServicePathMapping implements Mapper<ServicePathList, ServiceList> {


    @Override
    public ServicePathList toRequest(ServiceList serviceList) {
        // null design pattern
        return new ServicePathList();
    }

    @Override
    public ServiceList toResponse(ServicePathList servicePathList) {
        ServiceList serviceList = new ServiceList();
        serviceList.setServices(new ArrayList<>());
        if (servicePathList != null && servicePathList.getServicePathContent() != null) {
            for (ServicePath servicePath :
                    servicePathList.getServicePathContent().getServicePathList()) {
                Service service = new Service();
                service.setServiceName(servicePath.getServicePathName());
                sortByNodeId(servicePath);

                Node firstNode = getFirstNode(servicePath);
                Node lastNode = getLastNode(servicePath);

                LinkPoint firstLinkPoint = getLinkPoint(servicePath, firstNode);
                LinkPoint secondLinkPoint = getLinkPoint(servicePath, lastNode);

                service.setSourcePoint(firstLinkPoint);
                service.setTargetPoint(secondLinkPoint);

                serviceList.getServices().add(service);

            }

        }
        return serviceList;
    }

    private LinkPoint getLinkPoint(ServicePath servicePath, Node lastNode) {
        LinkPoint secondLinkPoint = new LinkPoint();
        secondLinkPoint.setNodeId(lastNode.getResource().getTpNodeId());
        secondLinkPoint.setServiceFormat(servicePath.getServiceZEnd().getServiceFormat());
        secondLinkPoint.setServiceRate(servicePath.getServiceZEnd().getServiceRate());
        return secondLinkPoint;
    }

    private Node getLastNode(ServicePath servicePath) {
        return servicePath.getPathDescription().getAToZDirection().getNodeList()
                .get(servicePath.getPathDescription().getAToZDirection().getNodeList().size() - 1);
    }

    private Node getFirstNode(ServicePath servicePath) {
        return servicePath.getPathDescription().getAToZDirection().getNodeList().get(0);
    }

    private void sortByNodeId(ServicePath servicePath) {
        servicePath.getPathDescription().getAToZDirection().getNodeList().sort((node1, node2) -> {
            Integer firstValue = Integer.parseInt(node1.getId());
            Integer secondValue = Integer.parseInt(node2.getId());
            return firstValue.compareTo(secondValue);
        });
    }
}
