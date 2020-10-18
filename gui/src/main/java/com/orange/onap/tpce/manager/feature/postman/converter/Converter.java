package com.orange.onap.tpce.manager.feature.postman.converter;

public interface Converter<T, R> {
    R convert(T t);
}
