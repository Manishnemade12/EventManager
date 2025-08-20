package com.quantilearn.api_gateway.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public WebProperties.Resources resources(){ // for global exception handling
        return new WebProperties.Resources();
    }
}
