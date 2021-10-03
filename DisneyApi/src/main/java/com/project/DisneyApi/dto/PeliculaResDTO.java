package com.project.DisneyApi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class PeliculaResDTO extends BaseDTO{
	
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
	
	private List<PersonajeForPeliculaResDTO>personajes = new ArrayList<PersonajeForPeliculaResDTO>();
	
}
