package org.example;
import org.example.course.Course;
import org.example.course.CourseConfig;
import org.example.course.Instructor;
import org.example.identity.Address;
import org.example.identity.IdentityConfig;
import org.example.identity.Phone;
import org.example.student.Student;
import org.example.student.StudentConfig;
import org.example.student.StudentDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class App {
    public static void main( String[] args ) {

//        AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(StudentConfig.class);
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(StudentConfig.class);
        appContext.register(IdentityConfig.class);
        appContext.register(CourseConfig.class);
        appContext.refresh();


        // uses the default constructor with no args
        // + field injection + setter injection
        Student student1 = appContext.getBean(Student.class);
        System.out.println( "STUDENT CLASS 1: " + student1 + "\n");

        // uses parameterized constructor with args
        // + field injection + setter injection
        Student student2 = appContext.getBean(Student.class,
                99,
                "Henry",
                appContext.getBean(Course.class),
                appContext.getBean(Course.class),
                List.of(appContext.getBean(Phone.class)),
                appContext.getBean(Address.class));
        System.out.println( "STUDENT CLASS 2: " + student2 + "\n");

        // uses the constructor with parameters,
        // but passed in value of city does not seem to override the default field value
        Address address1 = appContext.getBean(Address.class,
                "small town", "big city", "bigger state", "99999");
        System.out.println( "ADDRESS 1 W/ ARGS: " + address1 + "\n" );

        // uses the default address constructor with no args
        Address address2 = appContext.getBean(Address.class);
        System.out.println("ADDRESS 2: " + address2 + "\n");

        // uses the default phone constructor with no args
        Phone phone1 = appContext.getBean(Phone.class);
        System.out.println("PHONE 1: " + phone1 + "\n");

        // uses the default phone constructor with no args
        Phone phone2 = appContext.getBean(Phone.class, "999-0000");
        System.out.println("PHONE 2: " + phone2 + "\n");

        // uses the default course constructor
        Course course1 = appContext.getBean(Course.class);
        System.out.println("Course 1: " + course1 + "\n");

        // uses the default constructor with no instantiated objects
        StudentDTO studentDTO1 = appContext.getBean("studentDTO", StudentDTO.class);
        System.out.println( "STUDENT DTO 1:" + studentDTO1 + "\n");

        // uses the constructor with args of instantiated bean instances
        StudentDTO studentDTO2 = appContext.getBean(StudentDTO.class,
                appContext.getBean(Course.class),
                appContext.getBean(Course.class),
                appContext.getBean(Student.class),
                appContext.getBean(Address.class),
                List.of(List.of(appContext.getBean(Phone.class))),
                List.of(appContext.getBean(Phone.class)),
                List.of("111-1111", "222-2222")
        );
        System.out.println( "STUDENT DTO 2:" + studentDTO2 + "\n");


        appContext.close();
    }
}



//        StudentDTO studentDTO1 = appContext.getBean("studentDTO", StudentDTO.class);
//        StudentDTO studentDTO2 = appContext.getBean("studentDTO", StudentDTO.class);

// This should the default StudentDTO constructor, which uses the default for all objects
//        System.out.println( "STUDENT 1:\n" + studentDTO1 );
//        System.out.println( "Phone List A:\n" + studentDTO1.getPhoneListA() );
//        System.out.println( "Phone List B:\n" + studentDTO1.getPhoneListB() );
//        System.out.println( "String List C:\n" + studentDTO1.getStringList() );
