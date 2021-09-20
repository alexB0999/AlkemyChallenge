package com.project.DisneyApi.dto;

import com.project.DisneyApi.entity.Genero;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class PeliculaForPersonajeDTO {

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

    private String genero;
}
