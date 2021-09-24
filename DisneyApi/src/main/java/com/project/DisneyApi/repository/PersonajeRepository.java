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

	@Query(value = "SELECT p FROM Personaje p WHERE (p.nombre LIKE %:name% AND p.edad = :age) OR (p.nombre LIKE %:name% OR p.edad = :age)",nativeQuery = false)
	List<Personaje> search(@Param("name") String filtro, @Param("age") int filtro2);
	
}
