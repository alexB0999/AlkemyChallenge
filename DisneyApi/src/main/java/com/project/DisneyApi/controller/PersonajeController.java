package com.project.DisneyApi.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.DisneyApi.dto.ListPersonajesDTO;
import com.project.DisneyApi.dto.PeliculaForPersonajeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DisneyApi.dto.Mensaje;
import com.project.DisneyApi.dto.PersonajeDTO;
import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.service.PersonajeServiceImpl;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonajeController {
	
	@Autowired
	PersonajeServiceImpl personajeService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Personaje>> list(){
		List<Personaje> list = personajeService.list();
		ListPersonajesDTO personajeDTO = new ListPersonajesDTO();
		List<ListPersonajesDTO>personajes = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			personajeDTO.setNombre(list.get(i).getNombre());
			personajes.add(personajeDTO);
		}
		return new ResponseEntity(personajes, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<PersonajeDTO> getById(@PathVariable("id") Long id){
		if(!personajeService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Personaje personaje = personajeService.getOne(id).get();

		PersonajeDTO personajeDTO = new PersonajeDTO();
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEdad(personaje.getEdad());
		personajeDTO.setPeso(personaje.getPeso());
		personajeDTO.setHistoria(personaje.getHistoria());

		PeliculaForPersonajeDTO peliculaForPersonajeDTO = new PeliculaForPersonajeDTO();

		List<PeliculaForPersonajeDTO>peliculas = new ArrayList<>();

		for (int i = 0; i < personaje.getPeliculas().size(); i++) {
			peliculaForPersonajeDTO.setTitulo(personaje.getPeliculas().get(i).getTitulo());
			peliculaForPersonajeDTO.setFechaCreacion(personaje.getPeliculas().get(i).getFechaCreacion());
			peliculaForPersonajeDTO.setCalificacion(personaje.getPeliculas().get(i).getCalificacion());
			peliculaForPersonajeDTO.setGenero(personaje.getPeliculas().get(i).getGenero().getNombre());
			peliculas.add(peliculaForPersonajeDTO);
		}

		personajeDTO.setPeliculas(peliculas);
		return new ResponseEntity(personajeDTO, HttpStatus.OK);
	}
	
	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Personaje> getByNombre(@PathVariable("nombre") String nombre){
		if(!personajeService.existsByNombre(nombre)) 
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Personaje personaje = personajeService.getByNombre(nombre).get();
		return new ResponseEntity(personaje, HttpStatus.OK);
	}
	
	@GetMapping("/detailage/{edad}")
	public ResponseEntity<List<Personaje>> getByEdad(@PathVariable("edad") int edad){
		if(!personajeService.existsByEdad(edad)) 
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		List<Personaje> list = personajeService.getByEdad(edad);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody PersonajeDTO personajeDTO){
		// Valido si se ingresa un Nombre
		if(StringUtils.isBlank(personajeDTO.getNombre()))
			return new ResponseEntity(new Mensaje("El Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		
		// Valido que no se repita el Nombre
		if(personajeService.existsByNombre(personajeDTO.getNombre()))
			return new ResponseEntity(new Mensaje("El Personaje ya existe"), HttpStatus.BAD_REQUEST);
		
		// Valido que la edad sea positiva
				if(personajeDTO.getEdad()<0) 
					return new ResponseEntity(new Mensaje("La Edad no puede ser menor a 0"), HttpStatus.BAD_REQUEST);
		
		// Valido que el peso sea positivo
		if(personajeDTO.getPeso()<0) 
			return new ResponseEntity(new Mensaje("El Peso no puede ser menor a 0"), HttpStatus.BAD_REQUEST);
		
		Personaje personaje = Personaje.builder().nombre(personajeDTO.getNombre()).edad(personajeDTO.getEdad()).peso(personajeDTO.getPeso()).
				historia(personajeDTO.getHistoria()).build();
		personajeService.save(personaje);
		return new ResponseEntity(new Mensaje("Personaje creado"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PersonajeDTO personajeDTO){
		// Valido existencia del personaje a actualizar
		if(!personajeService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		
		 //Valido que el nombre actualizado no se repita con otro existente
		if(personajeService.existsByNombre(personajeDTO.getNombre()) && personajeService.getByNombre(personajeDTO.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("El Nombre ya existe"), HttpStatus.BAD_REQUEST);
		
		// Valido que el titulo no sea vacio
		if(StringUtils.isBlank(personajeDTO.getNombre()))
			return new ResponseEntity(new Mensaje("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		
		// Valido que la edad sea positiva
		if(personajeDTO.getEdad()<0) 
			return new ResponseEntity(new Mensaje("La Edad no puede ser menor a 0"), HttpStatus.BAD_REQUEST);

		// Valido que el peso sea positivo
		if(personajeDTO.getPeso()<0) 
			return new ResponseEntity(new Mensaje("El Peso no puede ser menor a 0"), HttpStatus.BAD_REQUEST);
		
		Personaje personaje = personajeService.getOne(id).get();
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEdad(personajeDTO.getEdad());
		personaje.setPeso(personajeDTO.getPeso());
		personaje.setHistoria(personajeDTO.getHistoria());
		//personaje.setPeliculas(personajeDTO.getPeliculas());
		personajeService.save(personaje);
		return new ResponseEntity(new Mensaje("Personaje actualizado"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(!personajeService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		personajeService.delete(id);
		return new ResponseEntity(new Mensaje("Personaje eliminado"), HttpStatus.OK);
	}
	
}
