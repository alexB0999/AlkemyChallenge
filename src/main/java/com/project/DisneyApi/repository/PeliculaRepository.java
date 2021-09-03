package com.project.DisneyApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.DisneyApi.entity.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{
	
	Optional<Pelicula> findByTitulo(String nombre);
	boolean existsByTitulo(String nombre);
	
}
