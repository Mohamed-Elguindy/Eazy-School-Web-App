package com.elguindy.eazyschoolwebapp.repositry;

import com.elguindy.eazyschoolwebapp.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,String> {
    Roles getByRoleName(String name);
}
