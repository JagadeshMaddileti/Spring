package com.spring.demo.dao;

import com.spring.demo.entity.School;
import com.spring.demo.entity.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> getStudents();
    public void saveStudent(Student theStudent);
    public Student getStudent(int theId);
    public void deleteStudent(int theId);
}
