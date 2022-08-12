package org.example.identity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@ComponentScan("com.example.identity")
public class IdentityConfig {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Address address() {
        return new Address();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Address address(String city, String state, String country, String zipcode) {
        return new Address(city, state, country, zipcode);
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Phone phone() {
        return new Phone();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Phone phone(String number) {
        return new Phone(number);
    }

}
