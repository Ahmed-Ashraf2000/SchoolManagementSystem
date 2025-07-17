package com.spring.school.service;

import com.spring.school.model.Contact;
import com.spring.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Service
@RequestScope
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveMessageDetails(Contact contact) {
        log.info(String.valueOf(contact));
        contactRepository.save(contact);
    }
}
