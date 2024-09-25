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

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String listSchools(Model theModel){
        List<School> theStudent= schoolService.getSchools();
        theModel.addAttribute("schools",theStudent);
        return "list-school";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        School school=new School();
        theModel.addAttribute("school",school);
        return "school-form";
    }

    @PostMapping("/saveSchool")
    public String saveContact(@Valid @ModelAttribute("school") School theSchool,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "school-form";
        }
        schoolService.saveSchool(theSchool);
        return "redirect:/school/list";
    }

    @GetMapping("/{schoolId}/students")
    public String showAllStudentsOfSchool(Model theModel,@PathVariable("schoolId") int id){

        School school=schoolService.getSchool(id);
        System.out.println(school.getName());
        List<Student> students=school.getStudents();

        System.out.println(students);
        theModel.addAttribute("students",students);
        return "list-student";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(Model theModel,@RequestParam("schoolId") int id){

        School school=schoolService.getSchool(id);
        theModel.addAttribute("school",school);

        return "school-form";
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
        theModel.addAttribute("student", student);
        theModel.addAttribute("schoolId", schoolId); // Pass schoolId to the form
        return "student-form"; // Return the form view
    }

    @GetMapping("/{schoolId}/students/list")
    public String studentsList(@PathVariable int schoolId, Model model) {
        School school = schoolService.getSchool(schoolId);
        model.addAttribute("school", school);
        return "list-student";
    }

    @GetMapping("/{schoolId}/add-student")
    public String showAddSchoolStudentForm(@PathVariable int schoolId, Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/{schoolId}/add-student")
    public String addStudent(@PathVariable int schoolId,@Valid @ModelAttribute("student") Student student,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student-form";
        }
        School school = schoolService.getSchool(schoolId);
        student.setSchool(school);
        studentService.saveStudent(student);
        return "redirect:../" + schoolId + "/students";
    }

    @GetMapping("/{schoolId}/update-student/{studentId}")
    public String showUpdateStudentForm(@PathVariable int schoolId, @PathVariable int studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        model.addAttribute("student",student);
        model.addAttribute("schoolId", schoolId);
        return "student-form";
    }

    @PostMapping("/{schoolId}/update-student/{studentId}")
    public String updateStudent(@PathVariable int schoolId, @PathVariable int studentId,@Valid @ModelAttribute("student") Student student,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student-form";
        }
        student.setId(studentId);
        School school = schoolService.getSchool(schoolId);
        student.setSchool(school);
        studentService.saveStudent(student);
        System.out.println("redirect:/school/" + schoolId + "/students");
        return "redirect:/school/" + schoolId + "/students";
    }

    @GetMapping("/{schoolId}/delete-student/{studentId}")
    public String deleteStudent(@PathVariable int schoolId, @PathVariable int studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/school/" + schoolId + "/students";
    }

}
