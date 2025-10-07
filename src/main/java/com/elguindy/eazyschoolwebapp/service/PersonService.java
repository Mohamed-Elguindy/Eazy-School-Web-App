package com.elguindy.eazyschoolwebapp.service;

import com.elguindy.eazyschoolwebapp.constants.EazySchoolConstants;
import com.elguindy.eazyschoolwebapp.model.Person;
import com.elguindy.eazyschoolwebapp.model.Roles;
import com.elguindy.eazyschoolwebapp.repositry.ContactRepositry;
import com.elguindy.eazyschoolwebapp.repositry.PersonRepository;
import com.elguindy.eazyschoolwebapp.repositry.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository  personRepository;

    @Autowired
    private RolesRepository  rolesRepository;

    public boolean savePerson(Person person) {
        boolean saved = false;
        Roles role =rolesRepository.getByRoleName(EazySchoolConstants.STUDENT);
        person.setRoles(role);
        Person savedPerson = personRepository.save(person);
        if (savedPerson != null && savedPerson.getPersonId()>0) {
            saved = true;
        }
        return saved;

    }
}
