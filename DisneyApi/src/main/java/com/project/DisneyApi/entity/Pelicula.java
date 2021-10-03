package com.project.DisneyApi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class Pelicula extends BaseEntity{
	
	//@NonNull
	private String titulo;
	
	//@NonNull
	private Date fechaCreacion;
	
	@Min(1)
	@Max(5)
	//@NonNull
	private int calificacion;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "fk_genero")
	private Genero genero;

	@ManyToMany(mappedBy = "peliculas")
	private List<Personaje>personajes = new ArrayList<>();
	
}
