package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.Opakowanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpakowanieRepository extends JpaRepository<Opakowanie, Integer> {
    Page<Opakowanie> findAllBy(Pageable pageable);

    @Query("SELECT new com.pedryczpietrak.medicinedata.model.DTO.CountResult(kategoriaDostepnosci, count(*), count(*)) " +
            "FROM Opakowanie GROUP BY kategoriaDostepnosci")
    List<CountResult> countKategoriaDostepnosci();
}
