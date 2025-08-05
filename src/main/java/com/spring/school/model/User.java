package com.spring.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.school.annotation.FieldsValueMatch;
import com.spring.school.annotation.PasswordValidator;
import com.spring.school.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "email",
                fieldToMatch = "confirmEmail",
                message = "Email addresses must match",
                groups = ValidationGroups.Registration.class
        ),
        @FieldsValueMatch(
                field = "password",
                fieldToMatch = "confirmPassword",
                message = "Passwords must match",
                groups = ValidationGroups.Registration.class
        )
})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "user_name")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "active")
    private boolean active = true;

    @NotBlank(message = "Confirm email is required")
    @Email(message = "Please provide a valid confirmation email")
    @Transient
    private String confirmEmail;

    @Pattern(regexp = "(^$|^\\+?[0-9]{10,15}$)",
            message = "Phone number must be valid and between 10-15 digits")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Password is required")
    @PasswordValidator
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Confirm password is required")
    @Transient
    private String confirmPassword;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private PrimeClass primeClass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}