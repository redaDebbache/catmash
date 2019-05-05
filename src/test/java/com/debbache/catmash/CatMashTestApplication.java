package com.debbache.catmash;

import com.debbache.catmash.configuration.ExcludeFromTests;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromTests.class))
@Profile("test")
public class CatMashTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatMashTestApplication.class, args);
    }
}
