package com.aaronmedlock.yipycore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="${propertyFile}", ignoreResourceNotFound = true)
public class YipyCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(YipyCoreApplication.class, args);
    }

}
