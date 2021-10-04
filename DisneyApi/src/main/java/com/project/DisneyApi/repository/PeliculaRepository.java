package com.project.DisneyApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.DisneyApi.entity.Pelicula;

@Repository
public interface PeliculaRepository extends BaseRepository<Pelicula, Long>{
	
	Optional<Pelicula> findByTitulo(String nombre);
	boolean existsByTitulo(String nombre);

	@Query(value = "SELECT p FROM Pelicula p, Genero g " +
			"WHERE (p.titulo LIKE %:name% AND g.id = :id_genero) ", nativeQuery = false)
	List<Pelicula> search(@Param("name") String filtro, @Param("id_genero") Long id, Sort Sort);
	
}
