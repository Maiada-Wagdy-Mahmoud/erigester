package edu.mum.eregistrar.controller;

import edu.mum.eregistrar.model.Student;
import edu.mum.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {
@Autowired
private StudentService studentService;
    /*@Autowired
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }*/
    @GetMapping(value = {"/eregistar/Student/list"})
    public ModelAndView listBooks(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", studentService.getAllStudentPaged(pageno));
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("Student/list");
        return modelAndView;
    }
    @GetMapping(value = {"/eregistar/Student/search"})
    public ModelAndView searchStudents(@RequestParam String studentNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", studentService.findByStudentNumber(studentNumber));
        modelAndView.addObject("currentPageNo", 0);
        modelAndView.setViewName("Student/list");
        return modelAndView;
    }
    @GetMapping(value = {"/eregistar/Student/new"})
    public String displayNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "Student/new";
    }
    @PostMapping(value = {"/eregistar/Student/new"})
    public String addNewStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Student/new";
        }
        student = studentService.saveStudent(student);
        return "redirect:/elibrary/book2/list";
    }
    @GetMapping(value = {"/eregistar/Student/edit/{studentId}"})
    public String editStudent(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "Student/edit";
        }
        return "Student/list";
    }
    @PostMapping(value = {"/eregistar/Student/edit"})
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Student/edit";
        }
        student = studentService.saveStudent(student);
        return "redirect:/eregistar/Student/list";
    }
    @GetMapping(value = {"/eregistar/Student/delete/{studentId}"})
    public String deleteStudent(@PathVariable Long studentId, Model model) {
        studentService.deleteStudentById(studentId);
        return "redirect:/eregistar/Student/list";
    }


}
