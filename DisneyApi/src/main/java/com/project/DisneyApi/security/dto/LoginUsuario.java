package com.project.DisneyApi.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUsuario {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
