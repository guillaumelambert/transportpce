package com.orange.onap.tpce.manager.common.util.rest;

import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.Networks;
import com.orange.onap.tpce.manager.feature.openroadm.topology.domain.RawNetwork;
import com.orange.onap.tpce.manager.feature.services.domain.ServiceListBody;
import com.orange.onap.tpce.manager.feature.services.domain.service.path.ServicePathList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
@Profile({"dev", "prod"})
public class RestTemplateServiceImpl implements RestTemplateService {

    private RestTemplate restTemplate;

    @Value("${onap.api.network-url-path}")
    private String networkPathUrl;

    @Value("${onap.api.service-url-path}")
    private String servicePathUrl;

    @Value("${onap.api.service-path-list}")
    private String servicePathList;


    @Autowired
    public RestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RawNetwork fetchTopology() {

        HttpEntity<?> entity = getHttpEntityHeaders();
        UriComponentsBuilder requestBuilder = UriComponentsBuilder.fromHttpUrl(networkPathUrl);
        HttpEntity<Networks> response = restTemplate.exchange(requestBuilder.toUriString(), HttpMethod.GET, entity,
                Networks.class);
        return Objects.requireNonNull(response.getBody()).getNetworks();
    }

    @Override
    public ServiceListBody fetchService() {
        HttpEntity<?> entity = getHttpEntityHeaders();
        UriComponentsBuilder requestBuilder = UriComponentsBuilder.fromHttpUrl(servicePathUrl);
        System.out.println(restTemplate.exchange(requestBuilder.toUriString(), HttpMethod.GET, entity, String.class));
        HttpEntity<ServiceListBody> response = restTemplate.exchange(requestBuilder.toUriString(), HttpMethod.GET, entity,
                ServiceListBody.class);

        return response.getBody();
    }

   /* private ServiceListBody mapServiceList(ServiceListBody body) {
        for (int i = 0; i < body.getServiceList().getServices().size(); i++) {
            System.out.println("happened");
            com.orange.onap.tpce.manager.feature.services.domain.Service service = body.getServiceList().getServices().get(i);
            System.out.println(service.getSourcePoint().getTxDirection().getPort().getDeviceName());
            System.out.println(service.getTargetPoint().getTxDirection().getPort().getDeviceName());
            service.getSourcePoint().setNodeId(service.getSourcePoint().getTxDirection().getPort().getDeviceName());
            service.getTargetPoint().setNodeId(service.getTargetPoint().getRxDirection().getPort().getDeviceName());
        }
        return body;
    }*/

    @Override
    public ServicePathList getServicePathContent() {
        HttpEntity<?> entity = getHttpEntityHeaders();
        UriComponentsBuilder requestBuilder = UriComponentsBuilder.fromHttpUrl(servicePathList);
        HttpEntity<ServicePathList> response = restTemplate.exchange(requestBuilder.toUriString(), HttpMethod.GET, entity,
                ServicePathList.class);


        return response.getBody();
    }

    private HttpEntity<?> getHttpEntityHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
