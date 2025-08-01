package com.spring.school.controller;

import com.spring.school.model.Course;
import com.spring.school.model.PrimeClass;
import com.spring.school.model.User;
import com.spring.school.service.ClassService;
import com.spring.school.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ClassService classService;
    private final CourseService courseService;

    @Autowired
    public AdminController(ClassService classService, CourseService courseService) {
        this.classService = classService;
        this.courseService = courseService;
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

    @GetMapping("/displayCourses")
    public String displayCourses(@ModelAttribute("course") Course course, Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses_secure";
    }

    @PostMapping("/addNewCourse")
    public String addNewCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "classes";
        }
        courseService.addNewCourse(course);
        return "redirect:/admin/displayCourses";
    }

    @GetMapping("/viewStudents/{id}")
    public String viewStudents(@ModelAttribute("person") User user,
                               @PathVariable("id") String courseId, Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "course-students";
    }

    @PostMapping("/addStudentToCourse")
    public String addStudentToCourse(@ModelAttribute("course") Course course,
                                     @ModelAttribute("person") User student) {
        courseService.addStudentToCourse(student.getEmail(), course.getCourseId());
        return "redirect:/admin/viewStudents/" + course.getCourseId();
    }

    @GetMapping("/deleteStudentFromCourse/{studentId}/{courseId}")
    public String deleteStudentFromCourse(@PathVariable("studentId") String studentId,
                                          @PathVariable("courseId") String courseId) {
        courseService.removeStudentFromCourse(Long.valueOf(studentId), Long.valueOf(courseId));
        return "redirect:/admin/viewStudents/" + courseId;
    }

}
