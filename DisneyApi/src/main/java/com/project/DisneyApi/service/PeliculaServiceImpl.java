package com.project.DisneyApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.repository.BaseRepository;
import com.project.DisneyApi.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl extends BaseServiceImpl<Pelicula, Long> implements PeliculaService{
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	public PeliculaServiceImpl(BaseRepository<Pelicula, Long> baseRepository) {
        super(baseRepository);
    }
	
	public List<Pelicula> list(){
		return peliculaRepository.findAll();
	}
	
	public Optional<Pelicula> getOne(Long id){
		return peliculaRepository.findById(id);
	}
	
	public Optional<Pelicula> getByTitulo(String nombre){
		return peliculaRepository.findByTitulo(nombre);
	}
	
	public void save (Pelicula pelicula) {
		peliculaRepository.save(pelicula);
	}
	
	public void delete (Long id) {
		peliculaRepository.deleteById(id);
	}
	
	public boolean existsById (Long id) {
		return peliculaRepository.existsById(id);
	}
	
	public boolean existsByTitulo (String nombre) {
		return peliculaRepository.existsByTitulo(nombre);
	}
	
}
