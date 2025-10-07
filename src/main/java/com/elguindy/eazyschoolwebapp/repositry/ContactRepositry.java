package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.constants.EazySchoolConstants;
import com.elguindy.eazyschoolwebapp.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ContactRepositry extends CrudRepository<Contact, Integer> {
    List<Contact> findByStatus(String eazySchoolConstants);
}
