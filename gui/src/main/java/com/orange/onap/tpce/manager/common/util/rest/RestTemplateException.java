package com.orange.onap.tpce.manager.common.util.rest;

import org.springframework.http.HttpStatus;

public class RestTemplateException extends RuntimeException {
    private HttpStatus statusCode;
    private String error;

    public RestTemplateException(HttpStatus statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }
}
