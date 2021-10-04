package com.project.DisneyApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonajeForPeliculaResDTO extends BaseDTO{

    @NotBlank
    private String nombre;

    private int edad;

    private float peso;

    private String historia;
}
