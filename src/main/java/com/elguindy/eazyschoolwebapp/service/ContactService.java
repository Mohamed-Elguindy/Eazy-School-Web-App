package com.elguindy.eazyschoolwebapp.service;

import com.elguindy.eazyschoolwebapp.constants.EazySchoolConstants;
import com.elguindy.eazyschoolwebapp.controller.contactController;
import com.elguindy.eazyschoolwebapp.model.Contact;
import com.elguindy.eazyschoolwebapp.repositry.ContactRepositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactService {

    @Autowired
    private ContactRepositry contactRepositry;

    private static Logger log = LoggerFactory.getLogger(contactController.class);


    public boolean saveMessage(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
       //contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        //contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepositry.save(contact);
        if( savedContact != null  && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepositry.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepositry.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
           // contact1.setUpdatedBy(updatedBy);
           // contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepositry.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            isUpdated = true;
        }
        return isUpdated;
    }


}
