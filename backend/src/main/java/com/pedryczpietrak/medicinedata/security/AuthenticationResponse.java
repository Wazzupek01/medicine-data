package com.pedryczpietrak.medicinedata.security;

import com.pedryczpietrak.medicinedata.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String nickname;
    private Role role;
}
