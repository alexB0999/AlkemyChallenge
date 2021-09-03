package com.project.DisneyApi.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.entity.Personaje;

public class PersonajeDTO {
	
	@NotBlank
	private String nombre;
	
	private int edad;
	
	private float peso;
	
	private String historia;
	
	private List<Pelicula>peliculas = new ArrayList<Pelicula>();

	public PersonajeDTO() {
		super();
	}

	public PersonajeDTO(String nombre, int edad, float peso, String historia) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
	}
	
	

	public PersonajeDTO(@NotBlank String nombre, int edad, float peso, String historia, List<Pelicula> peliculas) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	
	
	
}
