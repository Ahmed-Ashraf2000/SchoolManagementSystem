package com.spring.school.service;

import com.spring.school.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved;

        log.info(String.valueOf(contact));
        isSaved = true;

        return isSaved;
    }
}
