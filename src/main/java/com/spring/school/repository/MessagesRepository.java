package com.spring.school.repository;

import com.spring.school.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.school.model.Contact.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Contact, Integer> {
    @Transactional
    @Modifying
    @Query("update Contact c set c.status = :status where c.contactId = :id")
    void updateStatusById(int id, Status status);

    @Query("select c from Contact c where c.status = :status")
    Page<Contact> findByStatus(Status status, Pageable pageable);
}
