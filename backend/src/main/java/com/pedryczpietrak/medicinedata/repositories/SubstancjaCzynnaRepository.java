package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.SubstancjaCzynna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubstancjaCzynnaRepository extends JpaRepository<SubstancjaCzynna, Integer> {
}
