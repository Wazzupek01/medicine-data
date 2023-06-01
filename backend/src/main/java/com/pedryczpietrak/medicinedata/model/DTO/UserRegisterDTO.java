package com.pedryczpietrak.medicinedata.model.DTO;

import com.pedryczpietrak.medicinedata.model.enums.Role;
import com.pedryczpietrak.medicinedata.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
