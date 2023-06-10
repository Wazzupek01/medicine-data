package com.pedryczpietrak.medicinedata.model.DTO;

import com.pedryczpietrak.medicinedata.model.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDTO {
    private String email;
    private Role role;

    public AuthenticationResponseDTO(String email, Role role) {
        this.email = email;
        this.role = role;
    }
}
