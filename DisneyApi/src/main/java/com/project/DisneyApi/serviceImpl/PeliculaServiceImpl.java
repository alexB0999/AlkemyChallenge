package com.project.DisneyApi.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.project.DisneyApi.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.repository.BaseRepository;
import com.project.DisneyApi.repository.PeliculaRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class PeliculaServiceImpl extends BaseServiceImpl<Pelicula, Long> implements PeliculaService {
	
	@Autowired
	PeliculaRepository peliculaRepository;

	@Override
	public List<Pelicula> search(String name, Long id, String sort) throws Exception {
		try {

			List<Pelicula> peliculas = peliculaRepository.search(name, id, Sort.by(sort));

			return peliculas;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Optional<Pelicula> getByTitulo(String nombre){
		return peliculaRepository.findByTitulo(nombre);
	}

	public boolean existsById (Long id) {
		return peliculaRepository.existsById(id);
	}
	
	public boolean existsByTitulo (String nombre) {
		return peliculaRepository.existsByTitulo(nombre);
	}
	
}
