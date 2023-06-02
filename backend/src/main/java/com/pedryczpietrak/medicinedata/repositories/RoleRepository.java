package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
}
