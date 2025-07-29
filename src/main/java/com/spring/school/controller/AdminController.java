package com.spring.school.controller;

import com.spring.school.model.PrimeClass;
import com.spring.school.model.User;
import com.spring.school.service.ClassService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    private final ClassService classService;

    @Autowired
    public AdminController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/displayClasses")
    public String displayClasses(@ModelAttribute("primeClass") PrimeClass primeClass, Model model) {
        model.addAttribute("primeClasses", classService.getAllClasses());
        return "classes";
    }

    @PostMapping("/addNewClass")
    public String addNewClass(@Valid @ModelAttribute("primeClass") PrimeClass primeClass,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "classes";
        }
        classService.addClass(primeClass);
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/deleteClass/{id}")
    public String deleteClass(@PathVariable String id) {
        classService.deleteClass(Integer.parseInt(id));
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/displayStudents/{id}")
    public String displayStudents(@PathVariable String id, @ModelAttribute("person") User user,
                                  Model model) {
        PrimeClass primeClass = classService.getClassById(Long.valueOf(id));
        model.addAttribute("primeClass", primeClass);
        return "students";
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute("person") User student,
                             @ModelAttribute("primeClass") PrimeClass primeClass) {
        classService.addStudentToClass(student.getEmail(), primeClass.getClassId());
        return "redirect:/admin/displayStudents/" + primeClass.getClassId();
    }

    @GetMapping("/deleteStudent/{studentId}/{classId}")
    public String deleteStudent(@PathVariable Long studentId, @PathVariable Long classId) {
        classService.removeStudentFromCourse(studentId, classId);
        return "redirect:/admin/displayStudents/" + classId;
    }

}
