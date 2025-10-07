package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.model.Holiday;
import org.springframework.data.repository.CrudRepository;

public interface HolidaysRepository extends CrudRepository<Holiday,String> {

}
