package com.pedryczpietrak.medicinedata.services.interfaces;

import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;;
import org.springframework.data.domain.Page;

public interface ProduktLeczniczyService {
    Page<ProduktLeczniczyDTO> getAllProduktLeczniczyPage(int page, String sortBy, boolean isAscending);
}
