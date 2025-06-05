package com.example.pokemon.infra.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigHttp {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

