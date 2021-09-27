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

	@Query(value = "SELECT DISTINCT c FROM Personaje c, Pelicula p " +
			"WHERE (c.nombre LIKE %:name% AND c.edad = :age AND p.id = :id) " +
			"OR (c.nombre LIKE %:name% AND c.edad = :age OR p.id = :id) " +
			"OR (c.nombre LIKE %:name% OR c.edad = :age OR p.id = :id) " +
			"GROUP BY c.nombre", nativeQuery = false)
	List<Personaje> search(@Param("name") String name, @Param("age") int age, @Param("id") Long id);
}
