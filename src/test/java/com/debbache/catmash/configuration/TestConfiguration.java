package com.debbache.catmash.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    public RestTemplate catTemplate(){
        return mock(RestTemplate.class);
    }

    @Bean
    public TestRestTemplate testTemplate(@Value("${app.address}") String url){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));
        return testRestTemplate;
    }

}
