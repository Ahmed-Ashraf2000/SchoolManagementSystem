package com.spring.school.controller;

import com.spring.school.model.Contact;
import com.spring.school.service.MessagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessagesController {
    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/displayMessages")
    public String displayMessages(Model model) {
        List<Contact> contactMessages = messagesService.getAllOpenMessages();
        model.addAttribute("contactMessages", contactMessages);
        return "/messages";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(@ModelAttribute("contact") @Valid Contact contact,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }
        messagesService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @GetMapping("/closeMsg/{id}")
    public String closeMessage(@PathVariable("id") int messageId) {
        messagesService.changeMessageStatus(messageId);

        return "redirect:/displayMessages";
    }
}
