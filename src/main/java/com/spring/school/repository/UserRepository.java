package com.spring.school.repository;

import com.spring.school.model.PrimeClass;
import com.spring.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByPrimeClass(PrimeClass primeClass);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(Long studentId);
}
