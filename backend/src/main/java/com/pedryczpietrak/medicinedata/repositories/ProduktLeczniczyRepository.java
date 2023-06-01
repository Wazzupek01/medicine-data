package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.ProduktLeczniczy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktLeczniczyRepository extends JpaRepository<ProduktLeczniczy, Integer> {
}
