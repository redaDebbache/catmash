package com.debbache.catmash;

import com.debbache.catmash.configuration.ExcludeFromTests;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromTests.class))
public class CatMashTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatMashTestApplication.class, args);
    }
}
