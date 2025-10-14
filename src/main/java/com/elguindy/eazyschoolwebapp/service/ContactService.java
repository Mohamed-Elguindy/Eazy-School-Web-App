package com.elguindy.eazyschoolwebapp.service;

import com.elguindy.eazyschoolwebapp.constants.EazySchoolConstants;
import com.elguindy.eazyschoolwebapp.controller.contactController;
import com.elguindy.eazyschoolwebapp.model.Contact;
import com.elguindy.eazyschoolwebapp.repositry.ContactRepositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private ContactRepositry contactRepository;

    private static Logger log = LoggerFactory.getLogger(contactController.class);


    public boolean saveMessage(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
       //contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        //contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        if( savedContact != null  && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            isUpdated = true;
        }
        return isUpdated;
    }


}
