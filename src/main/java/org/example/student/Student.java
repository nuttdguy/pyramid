package org.example.student;

import org.example.course.Course;
import org.example.course.Instructor;
import org.example.identity.Address;
import org.example.identity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Student {

    private Instructor instructor;
    private Course course;

    @Value("2")
    private int id;
    @Value("Bob")
    private String name;

    @Autowired
    private List<Phone> phone;
    @Autowired
    private Address address;


    public Student() { }

    public Student(int id, String name, Instructor instructor, Course course, List<Phone> phone, Address address) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.course = course;
        this.phone = phone;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void destroy() {
        System.out.println("This is the Life-Cycle destroy method");
    }

    public void init() {
        System.out.println("This is the Life-Cycle init method");
    }

    public Instructor getInstructor() {
        return instructor;
    }

    @Autowired
    @Qualifier("course")
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }

    @Autowired
    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "\n Student{" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n phone=" + phone +
                ",\n address=" + address +
                ",\n instructor=" + instructor +
                ",\n course=" + course +
                "}";
    }
}
