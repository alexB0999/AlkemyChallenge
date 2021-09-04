package com.project.DisneyApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.repository.BaseRepository;
import com.project.DisneyApi.repository.PersonajeRepository;

@Service
public class PersonajeServiceImpl extends BaseServiceImpl<Personaje, Long> implements PersonajeService{
	
	@Autowired
	PersonajeRepository personajeRepository;
	
	public PersonajeServiceImpl(BaseRepository<Personaje, Long> baseRepository) {
        super(baseRepository);
    }
	
	public List<Personaje> list(){
		return personajeRepository.findAll();
	}
	
	public Optional<Personaje> getOne(Long id){
		return personajeRepository.findById(id);
	}
	
	public Optional<Personaje> getByNombre(String nombre){
		return personajeRepository.findByNombre(nombre);
	}
	
	public List <Personaje> getByEdad(int edad){
		return personajeRepository.findByEdad(edad);
	}
	
	public void save (Personaje personaje) {
		personajeRepository.save(personaje);
	}
	
	public void delete (Long id) {
		personajeRepository.deleteById(id);
	}
	
	public boolean existsById (Long id) {
		return personajeRepository.existsById(id);
	}
	
	public boolean existsByNombre (String nombre) {
		return personajeRepository.existsByNombre(nombre);
	}
	
	public boolean existsByEdad(int edad) {
		return personajeRepository.existsByEdad(edad);
	}
	
}
