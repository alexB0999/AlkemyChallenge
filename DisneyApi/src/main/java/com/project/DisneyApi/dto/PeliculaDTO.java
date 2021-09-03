package com.project.DisneyApi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.project.DisneyApi.entity.Genero;
import com.project.DisneyApi.entity.Personaje;

import lombok.NonNull;

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

	public PeliculaDTO() {
		super();
	}

	public PeliculaDTO(@NotBlank @NonNull String titulo, @NotBlank @NonNull Date fechaCreacion,
			@Min(1) @Max(5) @NotBlank int calificacion) {
		super();
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}
	
}
