package com.pedryczpietrak.medicinedata.services.interfaces;

import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduktLeczniczyService {
    Page<ProduktLeczniczyDTO> getAllProduktLeczniczyPage(int page, String sortBy, boolean isAscending);
    Page<ProduktLeczniczyDTO> getProduktLeczniczyByNamePage(String name, int page, String sortBy, boolean isAscending);
    List<String> listAllSortParameters();
}
