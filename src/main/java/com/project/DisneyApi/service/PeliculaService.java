package com.project.DisneyApi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.repository.PeliculaRepository;

@Service
@Transactional
public class PeliculaService {
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	public List<Pelicula> list(){
		return peliculaRepository.findAll();
	}
	
	public Optional<Pelicula> getOne(int id){
		return peliculaRepository.findById(id);
	}
	
	public Optional<Pelicula> getByTitulo(String nombre){
		return peliculaRepository.findByTitulo(nombre);
	}
	
	public void save (Pelicula pelicula) {
		peliculaRepository.save(pelicula);
	}
	
	public void delete (int id) {
		peliculaRepository.deleteById(id);
	}
	
	public boolean existsById (int id) {
		return peliculaRepository.existsById(id);
	}
	
	public boolean existsByTitulo (String nombre) {
		return peliculaRepository.existsByTitulo(nombre);
	}
	
}
