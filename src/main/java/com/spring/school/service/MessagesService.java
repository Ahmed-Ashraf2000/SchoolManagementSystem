package com.spring.school.service;

import com.spring.school.model.Contact;
import com.spring.school.repository.MessagesRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Transactional
    public void saveMessageDetails(Contact contact) {
        log.info(String.valueOf(contact));
        messagesRepository.save(contact);
    }

    public Page<Contact> getAllOpenMessages(String currentPage, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(Integer.parseInt(currentPage) - 1, 5, sort);
        return messagesRepository.findByStatus(Contact.Status.OPEN, pageable);
    }

    @Transactional
    public void changeMessageStatus(int messageId) {
        Contact contact = messagesRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));

        if (contact.getStatus() == Contact.Status.OPEN) {
            contact.setStatus(Contact.Status.CLOSED);
        }

        messagesRepository.save(contact);
    }
}
