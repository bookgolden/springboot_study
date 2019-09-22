package com.java.redis.config;

import com.java.redis.facotry.BuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {

    @Bean
    public BuilderFactory builderFactory(){
        return new BuilderFactory();
    }
}
