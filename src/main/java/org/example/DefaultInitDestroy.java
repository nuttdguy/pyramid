package org.example;

import org.springframework.context.annotation.Bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Bean(initMethod = "init", destroyMethod = "destroy")
public @interface DefaultInitDestroy { }
