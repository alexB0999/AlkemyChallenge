package com.project.DisneyApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class PersonajeForPeliculaResDTO {

    @NotBlank
    private String nombre;

    private int edad;

    private float peso;

    private String historia;
}
