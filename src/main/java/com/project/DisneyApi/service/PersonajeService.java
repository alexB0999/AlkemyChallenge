package com.project.DisneyApi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.repository.PersonajeRepository;

@Service
@Transactional
public class PersonajeService {
	
	@Autowired
	PersonajeRepository personajeRepository;
	
	public List<Personaje> list(){
		return personajeRepository.findAll();
	}
	
	public Optional<Personaje> getOne(int id){
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
	
	public void delete (int id) {
		personajeRepository.deleteById(id);
	}
	
	public boolean existsById (int id) {
		return personajeRepository.existsById(id);
	}
	
	public boolean existsByNombre (String nombre) {
		return personajeRepository.existsByNombre(nombre);
	}
	
	public boolean existsByEdad(int edad) {
		return personajeRepository.existsByEdad(edad);
	}
	
	
}
