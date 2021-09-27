package com.project.DisneyApi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Genero extends BaseEntity{
	
	@NonNull
	private String nombre;
	

	@OneToMany(mappedBy = "genero")
	private List<Pelicula>peliculas = new ArrayList<Pelicula>();
}
