package org.example;
import org.example.course.CourseConfig;
import org.example.identity.IdentityConfig;
import org.example.identity.Phone;
import org.example.student.Student;
import org.example.student.StudentConfig;
import org.example.student.StudentDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {

//        AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(StudentConfig.class);
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(StudentConfig.class);
        appContext.register(IdentityConfig.class);
        appContext.register(CourseConfig.class);
        appContext.refresh();

        StudentDTO studentDTO1 = appContext.getBean("studentDTO", StudentDTO.class);
//        StudentDTO studentDTO2 = appContext.getBean("studentDTO", StudentDTO.class);

        // This should the default StudentDTO constructor, which uses the default for all objects
//        System.out.println( "STUDENT 1:\n" + studentDTO1 );
        System.out.println( "Phone List A:\n" + studentDTO1.getPhoneListA() );
        System.out.println( "Phone List B:\n" + studentDTO1.getPhoneListB() );
        System.out.println( "String List C:\n" + studentDTO1.getStringList() );

//        StudentDTO studentDTO2 = new StudentDTO();
//        studentDTO2.setPhoneList(List.of(new Phone("000-0000"), new Phone("555-5555")));
//        System.out.println(studentDTO2.getPhoneList());


//        // This should produce default Student Object by create method, name = value
//        System.out.println( "STUDENT 2:\n" + studentDTO1.createStudent() );
//
//        System.out.println( "STUDENT DTO 1:\n" + studentDTO2 );
//
//        // This should produce default Student Object by create method, name = value
//        System.out.println( "STUDENT DTO 2:\n" + studentDTO2.createStudent() );
//
//        System.out.println(studentDTO1.equals(studentDTO2));
//        System.out.println(studentDTO1 == studentDTO2);


//        List<Phone> phoneList = new ArrayList<>();
//        Phone phone1 = appContext.getBean(Phone.class);
//        Phone phone2 = appContext.getBean(Phone.class);
//        phoneList.add(phone1);
//        phoneList.add(phone2);
//        System.out.println("Phone - Before : " + phoneList);
//
//        // change the number of phone 2
//        phone2.setNumber("111-1111");
//        phoneList.add(phone2);
//        System.out.println("Phone - After +1: " + phoneList);
//
//        System.out.println(phone1.equals(phone2));
//        System.out.println(phone1 == phone2);
//        System.out.println(phoneList.get(1) == phoneList.get(2));
//
//        // This should override the default class values
//        System.out.println( "STUDENT 3:\n" + studentDTO1.
//                createStudentWithProps(10, "Susan"));
//
//
//        Student student2 = appContext.getBean(Student.class);
////        student2.setName("Jane");
//        System.out.println( "STUDENT CLASS 1:\n" + student2 );
//
//        System.out.println(student2.equals(studentDTO1.getStudent()));
//        System.out.println(student2 == studentDTO1.getStudent());


        appContext.close();
    }
}
