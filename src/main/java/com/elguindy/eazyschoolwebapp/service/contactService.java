package com.elguindy.eazyschoolwebapp.service;

import com.elguindy.eazyschoolwebapp.controller.contactController;
import com.elguindy.eazyschoolwebapp.model.contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class contactService {
    private static Logger log = LoggerFactory.getLogger(contactController.class);


    public boolean saveMessage(contact contact) {
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }

}
