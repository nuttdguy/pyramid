package org.pygn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Implement consrtuctor and setter dependency injection / IOC
 *
 */
public class App {

    public static void main( String[] args ) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring_beans.xml");

        Student student = (Student) appContext.getBean("student");
        System.out.println(student);

        Phone phone = (Phone) appContext.getBean("phone");
        System.out.println(phone);

        Address address = (Address) appContext.getBean("address");
        System.out.println(address);


    }
}
