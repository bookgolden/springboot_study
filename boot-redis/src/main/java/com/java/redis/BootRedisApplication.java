package com.java.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
public class BootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRedisApplication.class, args);
    }

}
