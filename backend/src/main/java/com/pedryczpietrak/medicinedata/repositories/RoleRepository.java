package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(String name);
}
