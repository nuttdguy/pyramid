package org.example.student;

import org.example.DefaultInitDestroy;
import org.example.course.Course;
import org.example.course.Instructor;
import org.example.identity.Address;
import org.example.identity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@ComponentScan("com.example.student")
public class StudentConfig {


    @Bean
    @DefaultInitDestroy
    @Scope(SCOPE_PROTOTYPE)
    public Student student() { return new Student(); }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public StudentDTO studentDTO() {
        return new StudentDTO();
    }

    // Phone List A:
    // [[Phone{number='111-1111'}, Phone{number='222-2222'}]]
    @Bean
    List<Phone> getPhoneList() {
        Phone p1 = new Phone("111-1111");
        Phone p2 =  new Phone("222-2222");
        return Arrays.asList(p1, p2);
    }

    // Phone List B:
    // [Phone{number='888-8888'}, Phone{number='000-0000'}, Phone{number='null'}]
    @Bean Phone setPhoneB1() { return new Phone("888-8888"); }
    @Bean Phone setPhoneB2() { return new Phone("000-0000"); }

    // String List C:
    // [333-3333, 444-4444]
    @Bean
    List<String> getStringList() {
        return Arrays.asList("333-3333", "444-4444");
    }

}
