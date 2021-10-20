package com.project.DisneyApi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NuevoUsuario {

    @NotBlank
    private String nombre;

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Set<String> roles = new HashSet<>();

}
