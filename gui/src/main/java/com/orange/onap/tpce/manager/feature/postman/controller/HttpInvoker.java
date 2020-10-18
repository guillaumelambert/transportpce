package com.orange.onap.tpce.manager.feature.postman.controller;

import com.orange.onap.tpce.manager.feature.postman.model.HttpRequest;
import com.orange.onap.tpce.manager.feature.postman.service.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class HttpInvoker {

    @Autowired
    HttpRequester httpRequester;

    @PostMapping("/api/invoke")
    public String invoke(@RequestBody HttpRequest request) {
        return httpRequester.invokeRequest(request);
    }
}
