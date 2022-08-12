package org.example.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
@ComponentScan("org.example.course")
public class CourseConfig {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    Course course() {
        return new Course("000", "default");
    }

}
