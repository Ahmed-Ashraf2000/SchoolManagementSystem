package com.spring.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "courses")
@EntityListeners(AuditingEntityListener.class)
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @NotBlank(message = "Course name is required")
    @Column(name = "name", nullable = false)
    private String courseName;

    @NotBlank(message = "Course fees is required")
    @Column(name = "fees", nullable = false)
    private String fees;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "courses")
    private List<User> users = new ArrayList<>();
}
