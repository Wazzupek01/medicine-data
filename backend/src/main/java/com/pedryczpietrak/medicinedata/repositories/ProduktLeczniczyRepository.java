package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProduktLeczniczyRepository extends JpaRepository<ProduktLeczniczy, Integer> {
    Page<ProduktLeczniczy> findAllBy(Pageable pageable);
    Page<ProduktLeczniczy> findAllByNazwaProduktuContainingIgnoreCase(String nazwaProduktu, Pageable pageable);

//    @Query("SELECT new com.pedryczpietrak.medicinedata.model.DTO.CountResult(s.substancjaCzynna, COUNT(*)) FROM ProduktLeczniczy" +
//            " LEFT JOIN SubstancjeCzynne sc ON ProduktLeczniczy.substancjeCzynne.id = sc.id" +
//            " LEFT JOIN SubstancjaCzynna s ON sc.id = s.substancjeCzynne.id" +
//            " GROUP BY  s.substancjaCzynna ORDER BY COUNT(*) DESC LIMIT 12")

    @Query(nativeQuery = true, value = "select s.substancja_czynna, count(*) from produkt_leczniczy" +
            "    left join substancje_czynne sc on produkt_leczniczy.substancje_czynne_id = sc.id" +
            "    left join substancja_czynna s on sc.id = s.substancje_czynne_id" +
            "                                     group by  s.substancja_czynna order by count(*) DESC LIMIT 12;")
    List<Map<String, Long>> countSubstancjaCzynnaTop10();
}
