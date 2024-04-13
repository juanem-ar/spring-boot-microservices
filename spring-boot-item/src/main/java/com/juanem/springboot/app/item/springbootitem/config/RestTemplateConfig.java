package com.juanem.springboot.app.item.springbootitem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {
    @Bean("restClient")
    public RestTemplate restTemplateRegister(){
        return new RestTemplate();
    }
}
