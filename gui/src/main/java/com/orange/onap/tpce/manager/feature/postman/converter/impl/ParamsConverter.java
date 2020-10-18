package com.orange.onap.tpce.manager.feature.postman.converter.impl;

import com.orange.onap.tpce.manager.feature.postman.converter.Converter;
import com.orange.onap.tpce.manager.feature.postman.model.KeyValuePair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParamsConverter implements Converter<KeyValuePair[], Map<String, String>> {
    @Override
    public Map<String, String> convert(KeyValuePair[] keyValuePairs) {
        Map<String, String> paramsMap = new HashMap<>();
        Arrays.stream(keyValuePairs).forEach((pair) -> paramsMap.put(pair.getKey(), pair.getValue()));
        return paramsMap;
    }
}
