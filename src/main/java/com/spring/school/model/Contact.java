package com.spring.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @NotNull
    @Size(min = 1, max = 15, message = "Name must be between 1 and 15 characters")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number should be 10-15 digits")
    @Column(name = "mobile_num")
    private String mobileNum;

    @NotNull
    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 100, message = "Subject must not exceed 100 characters")
    @Column(name = "subject")
    private String subject;

    @NotNull
    @Size(max = 500, message = "Message must not exceed 500 characters")
    @Column(name = "message")
    private String message;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.OPEN;

    public enum Status {
        OPEN, CLOSED
    }
}
