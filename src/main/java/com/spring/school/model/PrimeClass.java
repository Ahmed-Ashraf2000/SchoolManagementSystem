package com.spring.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "class")
@EntityListeners(AuditingEntityListener.class)
public class PrimeClass extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @NotBlank(message = "Course name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "primeClass", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<User> persons = new ArrayList<>();
}
