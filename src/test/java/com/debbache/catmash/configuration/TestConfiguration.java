package com.debbache.catmash.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    @Primary
    public RestTemplate catTemplate(){
        return mock(RestTemplate.class);
    }
}
