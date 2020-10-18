package com.orange.onap.tpce.manager.feature.postman.model;

import lombok.Data;

@Data
public class HttpRequest {
    private String url;
    private HttpMethod method;
    private KeyValuePair[] headers;
    private KeyValuePair[] params;
    private String body;

    public HttpRequest(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }
}
