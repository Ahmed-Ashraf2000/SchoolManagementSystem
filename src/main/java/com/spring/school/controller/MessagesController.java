package com.spring.school.controller;

import com.spring.school.model.Contact;
import com.spring.school.service.MessagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String displayFirstPage() {
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";
    }

    @GetMapping("/displayMessages/page/{currentPage}")
    public String displayMessages(Model model, @PathVariable String currentPage,
                                  @RequestParam(defaultValue = "name") String sortField,
                                  @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Contact> page = messagesService.getAllOpenMessages(currentPage, sortField, sortDir);

        List<Contact> contactMessages = page.getContent();

        model.addAttribute("currentPage", Integer.parseInt(currentPage));
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("contactMessages", contactMessages);

        return "messages";
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

    @GetMapping("/closeMsg")
    public String closeMessage(@RequestParam("id") int messageId) {
        messagesService.changeMessageStatus(messageId);
        return "redirect:/displayMessages";
    }
}
