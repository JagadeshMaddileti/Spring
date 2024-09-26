package com.spring.demo.controller;

import com.spring.demo.entity.School;
import com.spring.demo.entity.Student;
import com.spring.demo.service.SchoolService;
import com.spring.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/school")
public class SchoolController {


    private final SchoolService schoolService;
    private final StudentService studentService;
    private static final String SCHOOL_ATTRIBUTE = "school";
    private static final String STUDENT_ATTRIBUTE = "student";

    @Autowired
    public SchoolController(SchoolService schoolService, StudentService studentService) {
        this.schoolService = schoolService;
        this.studentService = studentService;
    }

    private String schoolForm="school-form";
    private String studentForm="student-form";

    @GetMapping("/list")
    public String listSchools(Model theModel){
        List<School> theStudent= schoolService.getSchools();
        theModel.addAttribute("schools",theStudent);
        return "list-school";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        School school=new School();
        theModel.addAttribute(SCHOOL_ATTRIBUTE,school);
        return schoolForm;
    }

    @PostMapping("/saveSchool")
    public String saveContact(@Valid @ModelAttribute("school") School theSchool,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return schoolForm;
        }
        schoolService.saveSchool(theSchool);
        return "redirect:/school/list";
    }

    @GetMapping("/{schoolId}/students")
    public String showAllStudentsOfSchool(Model theModel,@PathVariable("schoolId") int id){

        School school=schoolService.getSchool(id);
        List<Student> students=school.getStudents();

        theModel.addAttribute("students",students);
        return "list-student";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(Model theModel,@RequestParam("schoolId") int id){

        School school=schoolService.getSchool(id);
        theModel.addAttribute(SCHOOL_ATTRIBUTE,school);

        return schoolForm;
    }

    @GetMapping("/delete")
    public String deleteSchool(@RequestParam("schoolId") int theId){
        schoolService.deleteSchool(theId);
        return "redirect:/school/list";
    }

    @GetMapping("/{schoolId}/showFormForAdd")
    public String showFormForAddStudent(@PathVariable("schoolId") int schoolId, Model theModel) {
        // Create a new Student object or perform other logic
        Student student = new Student();
        theModel.addAttribute(STUDENT_ATTRIBUTE, student);
        theModel.addAttribute("schoolId", schoolId); // Pass schoolId to the form
        return studentForm; // Return the form view
    }

    @GetMapping("/{schoolId}/students/list")
    public String studentsList(@PathVariable int schoolId, Model model) {
        School school = schoolService.getSchool(schoolId);
        model.addAttribute(SCHOOL_ATTRIBUTE, school);
        return "list-student";
    }

    @GetMapping("/{schoolId}/add-student")
    public String showAddSchoolStudentForm(@PathVariable int schoolId, Model model) {
        Student student = new Student();
        model.addAttribute(STUDENT_ATTRIBUTE, student);
        return studentForm;
    }

    @PostMapping("/{schoolId}/add-student")
    public String addStudent(@PathVariable int schoolId,@Valid @ModelAttribute("student") Student student,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return studentForm;
        }
        School school = schoolService.getSchool(schoolId);
        student.setSchool(school);
        studentService.saveStudent(student);
        return "redirect:../" + schoolId + "/students";
    }

    @GetMapping("/{schoolId}/update-student/{studentId}")
    public String showUpdateStudentForm(@PathVariable int schoolId, @PathVariable int studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        model.addAttribute(STUDENT_ATTRIBUTE,student);
        model.addAttribute("schoolId", schoolId);
        return studentForm;
    }

    @PostMapping("/{schoolId}/update-student/{studentId}")
    public String updateStudent(@PathVariable int schoolId, @PathVariable int studentId,@Valid @ModelAttribute("student") Student student,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return studentForm;
        }
        student.setId(studentId);
        School school = schoolService.getSchool(schoolId);
        student.setSchool(school);
        studentService.saveStudent(student);
        return "redirect:/school/" + schoolId + "/students";
    }

    @GetMapping("/{schoolId}/delete-student/{studentId}")
    public String deleteStudent(@PathVariable int schoolId, @PathVariable int studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/school/" + schoolId + "/students";
    }

}
