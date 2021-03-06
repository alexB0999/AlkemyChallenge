package com.project.DisneyApi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Personaje extends BaseEntity{
	
	//@NonNull
	private String nombre;
	
	//@NonNull
	private int edad;
	
	//@NonNull
	private float peso;
	
	//@NonNull
	private String historia;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name =  "personaje_pelicula", 
	joinColumns = @JoinColumn(name = "personaje_id"),
	inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
	private List<Pelicula>peliculas = new ArrayList<Pelicula>();
	
}
