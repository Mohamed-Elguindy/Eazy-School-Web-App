package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.model.EazyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends JpaRepository<EazyClass, Integer> {

}
