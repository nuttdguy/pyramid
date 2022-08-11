package org.example.student;

import org.example.course.Course;
import org.example.course.Instructor;
import org.example.identity.Address;
import org.example.identity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class StudentDTO {

    @Autowired @Qualifier("course") private Instructor instructor;

    @Autowired private Course course;

    private Student student;

    @Autowired private Address address;

    @Autowired
    private List<List<Phone>> phoneListA;

    @Autowired
    private List<Phone> phoneListB;

    @Autowired
    private List<String> stringList;

    StudentDTO() { }

    public Student createStudent() {
        return student;
    }

    public Student createStudentWithProps(int id, String name) {
        student.setId(id);
        student.setName(name);
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<List<Phone>> getPhoneListA() {
        return phoneListA;
    }

    public void setPhoneListA(List<List<Phone>> phoneListA) {
        this.phoneListA = phoneListA;
    }

    public List<Phone> getPhoneListB() {
        return phoneListB;
    }

    public void setPhoneListB(List<Phone> phoneListB) {
        this.phoneListB = phoneListB;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
