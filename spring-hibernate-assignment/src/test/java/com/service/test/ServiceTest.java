package com.service.test;

import com.spring.demo.dao.SchoolDAO;
import com.spring.demo.dao.StudentDAO;
import com.spring.demo.entity.School;
import com.spring.demo.entity.Student;
import com.spring.demo.service.SchoolServiceImp;
import com.spring.demo.service.StudentServiceImp;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ServiceTest {

    @InjectMocks
    private SchoolServiceImp schoolService;

    @InjectMocks
    private StudentServiceImp studentService;

    @Mock
    private SchoolDAO schoolDAO;

    private School testSchool;

    @Mock
    private StudentDAO studentDAO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setting up a test school with students
        testSchool = new School();
        testSchool.setId(1);
        testSchool.setName("Springfield High");

        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        students.add(student);

        testSchool.setStudents(students);
    }

    @Test
    void testGetSchools() {
        List<School> schools = new ArrayList<>();
        schools.add(testSchool);

        when(schoolDAO.getSchools()).thenReturn(schools);

        List<School> result = schoolService.getSchools();

        assertEquals(1, result.size());
        assertEquals("Springfield High", result.get(0).getName());
        verify(schoolDAO, times(1)).getSchools();
    }

    @Test
    void testSaveSchool() {
        schoolService.saveSchool(testSchool);
        verify(schoolDAO, times(1)).saveSchool(testSchool);
    }

    @Test
    void testGetSchool() {
        when(schoolDAO.getSchool(anyInt())).thenReturn(testSchool);

        School result = schoolService.getSchool(1);

        assertEquals("Springfield High", result.getName());
        assertNotNull(result.getStudents());

        // Verifying that Hibernate initializes the students collection
        verify(schoolDAO, times(1)).getSchool(1);
        verify(schoolDAO, times(1)).getSchool(anyInt());
        Hibernate.initialize(result.getStudents());
        assertEquals(1, result.getStudents().size());
        assertEquals("John", result.getStudents().get(0).getFirstName());
    }

    @Test
    void testDeleteSchool() {
        schoolService.deleteSchool(1);
        verify(schoolDAO, times(1)).deleteSchool(1);
    }

    @Test
    void testGetStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        when(studentDAO.getStudents()).thenReturn(students);

        List<Student> result = studentService.getStudents();

        assertEquals(1, result.size());
        verify(studentDAO, times(1)).getStudents();
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        studentService.saveStudent(student);

        verify(studentDAO, times(1)).saveStudent(student);
    }

    @Test
    void testGetStudent() {
        int studentId = 1;
        Student student = new Student();
        when(studentDAO.getStudent(studentId)).thenReturn(student);

        Student result = studentService.getStudent(studentId);

        assertNotNull(result);
        verify(studentDAO, times(1)).getStudent(studentId);
    }

    @Test
    void testDeleteStudent() {
        int studentId = 1;
        studentService.deleteStudent(studentId);

        verify(studentDAO, times(1)).deleteStudent(studentId);
    }
}
