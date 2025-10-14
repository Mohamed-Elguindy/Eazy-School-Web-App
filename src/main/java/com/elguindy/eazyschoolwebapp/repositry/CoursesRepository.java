package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}
