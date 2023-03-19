package com.cryptodiff.marketMaps;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class ConfigurationRestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
