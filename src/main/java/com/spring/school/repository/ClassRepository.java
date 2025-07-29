package com.spring.school.repository;

import com.spring.school.model.PrimeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<PrimeClass, Integer> {
    Optional<PrimeClass> findByClassId(Long classId);
}
