package com.pedryczpietrak.medicinedata.model.mappers;

import com.pedryczpietrak.medicinedata.model.DTO.AuthenticationResponseDTO;
import com.pedryczpietrak.medicinedata.security.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationResponseMapper {
    AuthenticationResponseDTO authenticationResponseToAuthenticationResponseDTO(AuthenticationResponse authenticationResponse);
}
