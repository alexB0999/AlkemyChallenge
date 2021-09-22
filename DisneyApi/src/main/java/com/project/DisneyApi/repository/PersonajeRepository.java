package com.project.DisneyApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.DisneyApi.entity.Personaje;

@Repository
public interface PersonajeRepository extends BaseRepository<Personaje, Long>{
	
	Optional<Personaje>findByNombre(String nombre);
	boolean existsByNombre(String nombre);
	List<Personaje> findByEdad(int edad);
	boolean existsByEdad(int edad);

	@Query(value = "SELECT p FROM Personaje p WHERE p.nombre LIKE %:name%")
	List<Personaje> searchNombre(@Param("name") String filtro);

	@Query(value = "SELECT p FROM Personaje p WHERE p.edad LIKE %:age%")
	List<Personaje> searchEdad(@Param("age") String filtro);
	
}
