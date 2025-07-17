package com.spring.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contact_msg")
@EntityListeners(AuditingEntityListener.class)
public class Contact {
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
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    public Contact() {
    }

    enum Status {
        OPEN, CLOSED
    }
}
