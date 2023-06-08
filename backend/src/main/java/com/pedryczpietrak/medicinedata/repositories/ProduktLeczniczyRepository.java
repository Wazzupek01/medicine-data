package com.pedryczpietrak.medicinedata.repositories;

import com.pedryczpietrak.medicinedata.model.DTO.CountResult;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProduktLeczniczyRepository extends JpaRepository<ProduktLeczniczy, Integer> {
    Page<ProduktLeczniczy> findAllBy(Pageable pageable);
    Page<ProduktLeczniczy> findAllByNazwaProduktuContainingIgnoreCase(String nazwaProduktu, Pageable pageable);

    @Query(nativeQuery = true, value = "select s.substancja_czynna, count(*), sum(case refundowany when true then 1 else 0 end) from produkt_leczniczy" +
            " left join substancje_czynne sc on produkt_leczniczy.substancje_czynne_id = sc.id" +
            " left join substancja_czynna s on sc.id = s.substancje_czynne_id" +
            " group by  s.substancja_czynna order by count(*) desc limit 10")
    Collection<Object[]> countSubstancjaCzynnaTop10();

    @Query("select new com.pedryczpietrak.medicinedata.model.DTO.CountResult(postac, count(*), sum(case refundowany when true then 1 else 0 end))" +
            " from ProduktLeczniczy group by postac order by count(*) desc limit 10")
    List<CountResult> countPostacTop10();

    void deleteProduktLeczniczyById(Integer id);
}
