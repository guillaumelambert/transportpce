package com.orange.onap.tpce.manager.feature.postman.service;

import com.orange.onap.tpce.manager.feature.postman.converter.impl.HeadersConverter;
import com.orange.onap.tpce.manager.feature.postman.converter.impl.ParamsConverter;
import com.orange.onap.tpce.manager.feature.postman.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpRequester {

    @Autowired
    HeadersConverter headersConverter;

    @Autowired
    ParamsConverter paramsConverter;

    private RestTemplate restTemplate = new RestTemplate();

    public String invokeRequest(HttpRequest request) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(request.getBody(), headersConverter.convert(request.getHeaders()));
        Map<String, String> paramsMap = paramsConverter.convert(request.getParams());
        String result = null;
        switch (request.getMethod()) {
            case GET:
                result = restTemplate.exchange(request.getUrl(), HttpMethod.GET, requestEntity, String.class, paramsMap).getBody();
                break;
            case POST:
                result = restTemplate.exchange(request.getUrl(), HttpMethod.POST, requestEntity, String.class, paramsMap).getBody();
                break;
            case PUT:
                result = restTemplate.exchange(request.getUrl(), HttpMethod.PUT, requestEntity, String.class, paramsMap).getBody();
                break;
            case PATCH:
                result = restTemplate.exchange(request.getUrl(), HttpMethod.PATCH, requestEntity, String.class, paramsMap).getBody();
                break;
            case HEAD:
                result = restTemplate.exchange(request.getUrl(), HttpMethod.HEAD, requestEntity, String.class, paramsMap).getBody();
                break;
            case DELETE:
                result = restTemplate.exchange(request.getUrl(), HttpMethod.DELETE, requestEntity, String.class, paramsMap).getBody();
                break;
        }
        return result;
    }
}
