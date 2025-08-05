package com.spring.school.rest;

import com.spring.school.model.Contact;
import com.spring.school.service.MessagesService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*")
public class MessagesRestController {
    private final MessagesService messagesService;

    @Autowired
    public MessagesRestController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping
    public List<Contact> getAllMessages() {
        return messagesService.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@Valid @RequestBody Contact contact,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity.badRequest().body(errors);
        }

        Contact savedContact = messagesService.saveMessageDetails(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable int id) {
        try {
            if (messagesService.existsById(id)) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Message with ID " + id + " not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            messagesService.deleteById(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Message with ID " + id + " successfully deleted");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error deleting message with ID: {}", id, e);

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to delete message: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable int id,
                                           @RequestBody Map<String, Object> updates) {
        try {
            if (messagesService.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Message with ID " + id + " not found"));
            }

            Contact contact = messagesService.findById(id);

            if (updates.containsKey("status")) {
                try {
                    String statusValue = updates.get("status").toString();
                    contact.setStatus(Contact.Status.valueOf(statusValue.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.badRequest()
                            .body(Map.of("error",
                                    "Invalid status value. Allowed values are: OPEN, CLOSED"));
                }
            }

            if (updates.containsKey("subject")) {
                contact.setSubject(updates.get("subject").toString());
            }

            if (updates.containsKey("message")) {
                contact.setMessage(updates.get("message").toString());
            }

            Contact updatedContact = messagesService.saveMessageDetails(contact);
            return ResponseEntity.ok(updatedContact);

        } catch (Exception e) {
            log.error("Error updating message with ID: {}", id, e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to update message: " + e.getMessage()));
        }
    }
}
