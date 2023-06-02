package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}