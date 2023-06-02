package com.pedryczpietrak.medicinedata.model.DTO;

import com.pedryczpietrak.medicinedata.model.entities.Role;
import com.pedryczpietrak.medicinedata.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    @Password
    private String password;

    @Email
    private String email;

    @NotNull
    private Role role;
}
