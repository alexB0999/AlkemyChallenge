package com.project.DisneyApi.dto;

import com.project.DisneyApi.entity.Genero;
import com.project.DisneyApi.entity.Personaje;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class PeliculaReqDTO {

    @NotBlank
    @NonNull
    private String titulo;

    @NotBlank
    @NonNull
    private Date fechaCreacion;

    @Min(1)
    @Max(5)
    @NotBlank
    private int calificacion;

    private Genero genero;

    private List<Personaje> personajes = new ArrayList<>();
}
