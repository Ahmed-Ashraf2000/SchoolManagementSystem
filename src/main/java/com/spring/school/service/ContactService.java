package com.spring.school.service;

import com.spring.school.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved;

        log.info(String.valueOf(contact));
        isSaved = true;

        return isSaved;
    }
}
