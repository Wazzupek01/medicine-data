package com.pedryczpietrak.medicinedata.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLoginDTO() {
    }
}
