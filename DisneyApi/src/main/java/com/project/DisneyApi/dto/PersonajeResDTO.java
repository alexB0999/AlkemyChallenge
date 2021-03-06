package com.project.DisneyApi.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonajeResDTO extends BaseDTO{
	
	@NotBlank
	private String nombre;
	
	private int edad;
	
	private float peso;
	
	private String historia;
	
	private List<PeliculaForPersonajeResDTO>peliculas = new ArrayList<>();

}
