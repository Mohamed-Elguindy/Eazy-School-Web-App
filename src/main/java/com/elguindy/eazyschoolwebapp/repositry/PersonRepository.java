package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {

    Person readByEmail(String email);

    Person findByEmail(String email);
}
