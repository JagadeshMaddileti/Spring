package com.controller.test;

import com.spring.demo.controller.SchoolController;
import com.spring.demo.entity.School;
import com.spring.demo.entity.Student;
import com.spring.demo.service.SchoolService;
import com.spring.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ControllerTest {

    @InjectMocks
    private SchoolController schoolController;

    @Mock
    private SchoolService schoolService;

    @Mock
    private StudentService studentService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listSchools() {
        List<School> schools = new ArrayList<>();
        schools.add(new School(1, "School A", "Location A", new ArrayList<>()));
        schools.add(new School(2, "School B", "Location B", new ArrayList<>()));

        when(schoolService.getSchools()).thenReturn(schools);

        String viewName = schoolController.listSchools(model);

        verify(model).addAttribute("schools", schools);
        assertEquals("list-school", viewName);
    }
    @Test
    void testShowFormForAdd() {
        String viewName = schoolController.showFormForAdd(model);

        ArgumentCaptor<School> schoolCaptor = ArgumentCaptor.forClass(School.class);
        verify(model, times(1)).addAttribute(eq("school"), schoolCaptor.capture());

        assertNotNull(schoolCaptor.getValue());
        assertEquals("school-form", viewName);
    }

    @Test
    void testShowFormForAddStudent() {
        int schoolId = 1;
        Model model = mock(Model.class);

        String viewName = schoolController.showFormForAddStudent(schoolId, model);

        verify(model, times(1)).addAttribute(eq("student"), any(Student.class));
<<<<<<< Updated upstream
        verify(model, times(1)).addAttribute(eq("schoolId"), eq(schoolId));
=======
        verify(model, times(1)).addAttribute("schoolId", schoolId);
>>>>>>> Stashed changes
        assertEquals("student-form", viewName);
    }

    @Test
    void testShowUpdateStudentForm() {
        int schoolId = 1;
        int studentId = 1;
        Student mockStudent = new Student();
        when(studentService.getStudent(studentId)).thenReturn(mockStudent);

        String viewName = schoolController.showUpdateStudentForm(schoolId, studentId, model);

        verify(studentService, times(1)).getStudent(studentId);
        verify(model, times(1)).addAttribute(eq("student"), any(Student.class));
<<<<<<< Updated upstream
        verify(model, times(1)).addAttribute(eq("schoolId"), eq(schoolId));
=======
        verify(model, times(1)).addAttribute("schoolId", schoolId);
>>>>>>> Stashed changes

        assertEquals("student-form", viewName);
    }


    @Test
    void testStudentsList() {
        int schoolId = 1;
        School mockSchool = mock(School.class);
        when(schoolService.getSchool(schoolId)).thenReturn(mockSchool);

        String viewName = schoolController.studentsList(schoolId, model);

        verify(schoolService, times(1)).getSchool(schoolId);
        verify(model, times(1)).addAttribute(eq("school"), eq(mockSchool));

        assertEquals("list-student", viewName);
    }


    @Test
    void saveSchool_Success() {
        School school = new School();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = schoolController.saveContact(school, bindingResult);

        verify(schoolService).saveSchool(school);
        assertEquals("redirect:/school/list", viewName);
    }
    @Test
    void testShowAddSchoolStudentForm() {
        int schoolId = 1;
        String viewName = schoolController.showAddSchoolStudentForm(schoolId, model);

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(model, times(1)).addAttribute(eq("student"), studentCaptor.capture());

        assertNotNull(studentCaptor.getValue());
        assertEquals("student-form", viewName);
    }
    @Test
    void testShowAllStudentsOfSchool() {
        int schoolId = 1;
        School mockSchool = mock(School.class);
        List<Student> mockStudents = new ArrayList<>();
        when(schoolService.getSchool(schoolId)).thenReturn(mockSchool);
        when(mockSchool.getStudents()).thenReturn(mockStudents);

        String viewName = schoolController.showAllStudentsOfSchool(model, schoolId);

        verify(schoolService, times(1)).getSchool(schoolId);
        verify(model, times(1)).addAttribute(eq("students"), eq(mockStudents));

        assertEquals("list-student", viewName);
    }
    @Test
    void testShowFormForUpdate() {
        int schoolId = 1;
        School mockSchool = mock(School.class);
        when(schoolService.getSchool(schoolId)).thenReturn(mockSchool);

        String viewName = schoolController.showFormForUpdate(model, schoolId);

        verify(schoolService, times(1)).getSchool(schoolId);
        verify(model, times(1)).addAttribute(eq("school"), eq(mockSchool));

        assertEquals("school-form", viewName);
    }


    @Test
    void saveSchool_Failure() {
        School school = new School();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = schoolController.saveContact(school, bindingResult);

        verify(schoolService, never()).saveSchool(any());
        assertEquals("school-form", viewName);
    }


    @Test
    void deleteSchool() {
        int schoolId = 1;

        String viewName = schoolController.deleteSchool(schoolId);

        verify(schoolService).deleteSchool(schoolId);
        assertEquals("redirect:/school/list", viewName);
    }

    @Test
    void addStudent_Success() {
        int schoolId = 1;
        Student student = new Student();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = schoolController.addStudent(schoolId, student, bindingResult);

        verify(studentService).saveStudent(student);
        assertEquals("redirect:../" + schoolId + "/students", viewName);
    }

    @Test
    void addStudent_Failure() {
        int schoolId = 1;
        Student student = new Student();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = schoolController.addStudent(schoolId, student, bindingResult);

        verify(studentService, never()).saveStudent(any());
        assertEquals("student-form", viewName);
    }

    @Test
    void updateStudent_Success() {
        int schoolId = 1;
        int studentId = 1;
        Student student = new Student();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = schoolController.updateStudent(schoolId, studentId, student, bindingResult);

        verify(studentService).saveStudent(student);
        assertEquals("redirect:/school/" + schoolId + "/students", viewName);
    }

    @Test
    void updateStudent_Failure() {
        int schoolId = 1;
        int studentId = 1;
        Student student = new Student();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = schoolController.updateStudent(schoolId, studentId, student, bindingResult);

        verify(studentService, never()).saveStudent(any());
        assertEquals("student-form", viewName);
    }

    @Test
    void deleteStudent() {
        int schoolId = 1;
        int studentId = 1;

        String viewName = schoolController.deleteStudent(schoolId, studentId);

        verify(studentService).deleteStudent(studentId);
        assertEquals("redirect:/school/" + schoolId + "/students", viewName);
    }
}
