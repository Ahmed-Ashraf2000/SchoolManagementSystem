package com.spring.school.controller;

import com.spring.school.model.User;
import com.spring.school.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PublicController(UserService userService,
                            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String displayRegisterPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setConfirmPassword(encodedPassword);
            userService.registerNewUser(user, "STUDENT");
            return "redirect:/login";
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            bindingResult.rejectValue("email", "error.user", "Failed to register user");
            return "register";
        }
    }

}
