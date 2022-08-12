package org.example.student;

import org.example.course.Course;
import org.example.course.Instructor;
import org.example.identity.Address;
import org.example.identity.Phone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDTO {

    private Instructor instructor;
    private Course course;
    private Student student;
    private Address address;
    private List<List<Phone>> phoneListA;
    private List<Phone> phoneListB;
    private List<String> stringList;

    StudentDTO() { }

    public StudentDTO(Instructor instructor, Course course, Student student, Address address,
                      List<List<Phone>> phoneListA, List<Phone> phoneListB, List<String> stringList) {
        this.instructor = instructor;
        this.course = course;
        this.student = student;
        this.address = address;
        this.phoneListA = phoneListA;
        this.phoneListB = phoneListB;
        this.stringList = stringList;
    }


    @Override
    public String toString() {
        return "StudentDTO{" +
                "instructor=" + instructor +
                ", course=" + course +
                ", student=" + student +
                ", address=" + address +
                ", phoneListA=" + phoneListA +
                ", phoneListB=" + phoneListB +
                ", stringList=" + stringList +
                '}';
    }

}
