package com.pedryczpietrak.medicinedata.model.mappers;

import com.pedryczpietrak.medicinedata.model.DTO.SubstancjaCzynnaDTO;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.SubstancjaCzynna;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubstancjaCzynnaDTOMapper {
    SubstancjaCzynnaDTO substancjaCzynnaToSubstancjaCzynnaDTO(SubstancjaCzynna substancjaCzynna);
}
