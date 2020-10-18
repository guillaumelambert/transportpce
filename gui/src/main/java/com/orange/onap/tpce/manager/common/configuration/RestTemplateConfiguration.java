package com.orange.onap.tpce.manager.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {


    @Value("${onap.api.username}")
    private String username;

    @Value("${onap.api.password}")
    private String password;

    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate =  new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));
        return restTemplate;

    }
}
