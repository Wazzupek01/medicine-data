package com.pedryczpietrak.medicinedata.security;

import com.pedryczpietrak.medicinedata.model.entities.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
    private String email;
    private Role role;

    public AuthenticationResponse(String token, String email, Role role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }
}
