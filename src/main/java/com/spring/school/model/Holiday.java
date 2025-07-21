package com.spring.school.model;

import jakarta.persistence.*;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "holidays")
@EntityListeners(AuditingEntityListener.class)
public class Holiday extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id")
    private Long holidayId;

    @Column(name = "day", nullable = false)
    private LocalDate day;

    @Column(name = "reason", nullable = false, length = 100)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private Type type;


    public enum Type {
        FESTIVAL, FEDERAL
    }
}