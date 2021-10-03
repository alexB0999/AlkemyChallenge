package com.project.DisneyApi.dto;

import com.project.DisneyApi.entity.Pelicula;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class PersonajeReqDTO extends BaseDTO{

    @NotBlank
    private String nombre;

    private int edad;

    private float peso;

    private String historia;

    private List<Pelicula> peliculas = new ArrayList<>();
}
