package com.spring.school.controller;

import com.spring.school.model.User;
import com.spring.school.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/displayCourses")
    public String displayCourses(Authentication authentication, Model model, HttpSession session) {
        String username = authentication.getName();
        User student = userService.getTheUserByUsername(username);

        session.setAttribute("student", student);

        model.addAttribute("person", student);
        return "courses_enrolled";
    }
}
