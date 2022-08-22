package com.genspark.spring.item.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DomainConfig {

    @Bean
    @Scope(scopeName = "prototype")
    public Detail detail() {
        return new Detail();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public Review review() {
        return new Review();

    }
}
