package com.spring.school.controller;

import com.spring.school.model.Contact;
import com.spring.school.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String displayContactPage() {
        return "contact";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(@ModelAttribute Contact contact) {
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }
}
