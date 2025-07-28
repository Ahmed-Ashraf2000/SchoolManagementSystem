package com.spring.school.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Profile {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "(^$|^\\+?[0-9]{10,15}$)",
            message = "Phone number must be valid and between 10-15 digits")
    private String phone;

    @NotBlank(message = "Street address is required")
    @Size(max = 255, message = "Address1 must not exceed 255 character")
    private String address1;

    @Size(max = 255, message = "Address2 must not exceed 255 character")
    private String address2;

    @NotNull(message = "ZIP code is required")
    @Min(value = 1, message = "ZIP code must be positive")
    private int zipCode;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;
}