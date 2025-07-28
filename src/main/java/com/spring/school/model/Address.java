package com.spring.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank(message = "Street address is required")
    @Size(max = 255, message = "Address1 must not exceed 255 character")
    @Column(name = "address1", nullable = false)
    private String address1;

    @Size(max = 255, message = "Address2 must not exceed 255 character")
    @Column(name = "address2")
    private String address2;

    @NotNull(message = "ZIP code is required")
    @Min(value = 1, message = "ZIP code must be positive")
    @Column(name = "zip_code")
    private int zipCode;

    @NotBlank(message = "City is required")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "State is required")
    @Column(name = "state", nullable = false)
    private String state;

}