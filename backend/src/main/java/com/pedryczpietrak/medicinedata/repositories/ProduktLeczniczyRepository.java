package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktLeczniczyRepository extends JpaRepository<ProduktLeczniczy, Integer> {
    Page<ProduktLeczniczy> findAllBy(Pageable pageable);
    Page<ProduktLeczniczy> findAllByNazwaProduktuContainingIgnoreCase(String nazwaProduktu, Pageable pageable);
}
