package com.orange.onap.tpce.manager.feature.postman.converter.impl;

import com.orange.onap.tpce.manager.feature.postman.converter.Converter;
import com.orange.onap.tpce.manager.feature.postman.model.KeyValuePair;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HeadersConverter implements Converter<KeyValuePair[], HttpHeaders> {
    @Override
    public HttpHeaders convert(KeyValuePair[] keyValuePairs) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Arrays.stream(keyValuePairs).forEach((pair) -> httpHeaders.add(pair.getKey(), pair.getValue()));
        return httpHeaders;
    }
}
