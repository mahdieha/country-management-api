package com.country.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CountryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryApiApplication.class, args);
    }
}
