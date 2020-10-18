package com.orange.onap.tpce.manager.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.onap.tpce.manager.helper.NetworkHelper;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.Networks;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.RawNetwork;
import com.orange.onap.tpce.manager.common.util.rest.RestTemplateService;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceList;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceListBody;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Profile("test")
public class RestTemplateServiceMock implements RestTemplateService {

    private ObjectMapper objectMapper;

    public RestTemplateServiceMock(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public RawNetwork fetchTopology() {

//        RawNetwork rawNetwork = objectMapper.readValue(new FileReader("allnetworks_example.json"), RawNetwork.class);
        Networks rawNetwork = null;
        try {
            rawNetwork = objectMapper.readValue(NetworkHelper.getNetworkMockFileContent(),
                    Networks.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(rawNetwork).getNetworks();
    }

    //TODO needed to be removed
    @Override
    public ServiceListBody fetchService() {
        ServiceList serviceList = null;
        try {
            serviceList = objectMapper.readValue(NetworkHelper.getAllContent(),
                    ServiceList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceListBody();
    }

    @Override
    public ServicePathList getServicePathContent() {
        return null;
    }
}
