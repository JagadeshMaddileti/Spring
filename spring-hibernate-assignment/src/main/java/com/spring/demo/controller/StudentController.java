//package com.spring.demo.controller;
//
//import com.spring.demo.entity.School;
//import com.spring.demo.entity.Student;
//import com.spring.demo.service.SchoolService;
//import com.spring.demo.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/student")
//public class StudentController {
//    @Autowired
//    private StudentService studentService;
//
//    @GetMapping("/list")
//    public String listStudents(Model theModel){
//        List<Student> theStudent= studentService.getStudents();
//        theModel.addAttribute("students",theStudent);
//        return "list-student";
//    }
//
//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel){
//        Student student=new Student();
//        theModel.addAttribute("student",student);
//        return "student-form";
//    }
//
//    @PostMapping("/saveStudent")
//    public String saveStudent(@ModelAttribute("student") Student thestudent){
//        studentService.saveStudent(thestudent);
//        return "redirect:/schools/list";
//    }
//
//    @GetMapping("/showFormForUpdate")
//    public String showFormForUpdate(Model theModel,@RequestParam("studentId") int id){
//        Student student=studentService.getStudent(id);
//        theModel.addAttribute("student",student);
//
//        return "student-form";
//    }
//
//    @GetMapping("/delete")
//    public String deleteStudent(@RequestParam("studentId") int theId){
//        studentService.deleteStudent(theId);
//        return "redirect:/student/list";
//    }
//
//}
