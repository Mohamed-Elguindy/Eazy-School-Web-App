package com.elguindy.eazyschoolwebapp.service;

import com.elguindy.eazyschoolwebapp.controller.contactController;
import com.elguindy.eazyschoolwebapp.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private static Logger log = LoggerFactory.getLogger(contactController.class);


    public boolean saveMessage(Contact contact) {
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }

}
