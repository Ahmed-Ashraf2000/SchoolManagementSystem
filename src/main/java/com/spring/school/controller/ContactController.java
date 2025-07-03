package com.spring.school.controller;

import com.spring.school.model.Contact;
import com.spring.school.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(@ModelAttribute("contact") @Valid Contact contact,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }
}
