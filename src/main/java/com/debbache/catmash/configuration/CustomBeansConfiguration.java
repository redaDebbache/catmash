package com.debbache.catmash.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@ExcludeFromTests
public class CustomBeansConfiguration {

    @Bean
    public RestTemplate catTemplate(@Value("${remote.cats.source}") String catUrl){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(catUrl));
        return restTemplate;
    }

}
