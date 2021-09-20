package com.project.DisneyApi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class Genero extends BaseEntity{
	
	//@NonNull
	private String nombre;
	

	@OneToMany(mappedBy = "genero")
	@Exclude
	private List<Pelicula>peliculas = new ArrayList<Pelicula>();
}
