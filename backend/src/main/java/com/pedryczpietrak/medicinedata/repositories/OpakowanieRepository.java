package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.Opakowanie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpakowanieRepository extends JpaRepository<Opakowanie, Integer> {
}
