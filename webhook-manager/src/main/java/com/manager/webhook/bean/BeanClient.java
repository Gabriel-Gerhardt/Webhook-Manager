package com.manager.webhook.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanClient {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
