package com.project.DisneyApi.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.project.DisneyApi.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.repository.BaseRepository;
import com.project.DisneyApi.repository.PersonajeRepository;

@Service
public class PersonajeServiceImpl extends BaseServiceImpl<Personaje, Long> implements PersonajeService {
	
	@Autowired
	PersonajeRepository personajeRepository;
	
	public PersonajeServiceImpl(BaseRepository<Personaje, Long> baseRepository) {
        super(baseRepository);
    }

	@Override
	public List<Personaje> search(String name, Integer age, Long id) throws Exception {
		try {

			List<Personaje> personajes = personajeRepository.search(name, age, id);

			return personajes;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean existsById (Long id) {
		return personajeRepository.existsById(id);
	}

	public Optional<Personaje> getByNombre(String nombre){
		return personajeRepository.findByNombre(nombre);
	}
	
	public boolean existsByNombre (String nombre) {
		return personajeRepository.existsByNombre(nombre);
	}
	
}
