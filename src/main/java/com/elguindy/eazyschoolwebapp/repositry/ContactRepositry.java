package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.constants.EazySchoolConstants;
import com.elguindy.eazyschoolwebapp.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepositry extends JpaRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    Page<Contact> findByStatus(String status, Pageable pageable);
}
