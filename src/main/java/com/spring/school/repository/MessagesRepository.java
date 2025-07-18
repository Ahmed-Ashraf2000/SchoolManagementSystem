package com.spring.school.repository;

import com.spring.school.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.school.model.Contact.Status;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByStatus(Status status);
}
