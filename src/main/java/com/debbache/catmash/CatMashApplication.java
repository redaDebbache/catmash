package com.debbache.catmash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CatMashApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatMashApplication.class, args);
    }

}
