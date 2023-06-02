package com.pedryczpietrak.medicinedata.model.mappers;

import com.pedryczpietrak.medicinedata.model.DTO.OpakowanieDTO;
import com.pedryczpietrak.medicinedata.model.Opakowanie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpakowanieDTOMapper {
    OpakowanieDTO opakowanieToOpakowanieDTO(Opakowanie opakowanie);
}
