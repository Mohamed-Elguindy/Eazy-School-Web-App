package com.elguindy.eazyschoolwebapp.service;


import com.elguindy.eazyschoolwebapp.model.Address;
import com.elguindy.eazyschoolwebapp.model.Person;
import com.elguindy.eazyschoolwebapp.model.Profile;
import com.elguindy.eazyschoolwebapp.repositry.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private PersonRepository personRepository;

    public Profile DisplayProfile(Person person) {
        Profile profile = new Profile();
        profile.setEmail(person.getEmail());
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() !=null && person.getAddress().getAddressId()>0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        return profile;
    }

    public Person UpdateProfile(Profile profile , Person person) {
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress() ==null || !(person.getAddress().getAddressId()>0)){
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        Person savedPerson = personRepository.save(person);
        return savedPerson;

    }



}
