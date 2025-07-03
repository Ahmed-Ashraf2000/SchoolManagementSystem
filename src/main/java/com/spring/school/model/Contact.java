package com.spring.school.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Contact {
    @NotNull
    @Size(min = 1, max = 15, message = "Name must be between 1 and 15 characters")
    private String name;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number should be 10-15 digits")
    private String mobileNum;

    @NotNull
    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 100, message = "Subject must not exceed 100 characters")
    private String subject;

    @Size(max = 500, message = "Message must not exceed 500 characters")
    private String message;
}
