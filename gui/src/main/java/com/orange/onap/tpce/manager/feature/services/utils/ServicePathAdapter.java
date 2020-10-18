package com.orange.onap.tpce.manager.feature.services.utils;

import com.orange.onap.tpce.manager.feature.services.domain.ServiceList;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicePathAdapter {

    private ServicePathMapping servicePathMapping;

    @Autowired
    public ServicePathAdapter(ServicePathMapping servicePathMapping) {
        this.servicePathMapping = servicePathMapping;
    }

    public ServiceList getServiceListFromServicePathList(ServicePathList servicePathList) {
        return this.servicePathMapping.toResponse(servicePathList);
    }

}
