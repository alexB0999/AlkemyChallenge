package com.project.DisneyApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.DisneyApi.entity.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer>{
	
	Optional<Personaje>findByNombre(String nombre);
	boolean existsByNombre(String nombre);
	List<Personaje> findByEdad(int edad);
	boolean existsByEdad(int edad);
	
}
