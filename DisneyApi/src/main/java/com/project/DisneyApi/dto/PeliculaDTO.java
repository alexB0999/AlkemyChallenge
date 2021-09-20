package com.project.DisneyApi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.project.DisneyApi.entity.Genero;
import com.project.DisneyApi.entity.Personaje;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class PeliculaDTO {
	
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
	
	private List<Personaje>personajes = new ArrayList<Personaje>();
	
}
