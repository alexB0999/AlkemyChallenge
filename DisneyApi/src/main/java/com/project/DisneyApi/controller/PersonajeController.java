package com.project.DisneyApi.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.DisneyApi.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.serviceImpl.PersonajeServiceImpl;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonajeController extends BaseControllerImpl<PersonajeReqDTO, Personaje, PersonajeServiceImpl>{
	
	@Autowired
	PersonajeServiceImpl personajeService;

	@GetMapping("")
	public ResponseEntity<?> search(@RequestParam String name, String age, String movie) {
		Integer edad = 0;
		Long idPelicula = Long.valueOf(0);
		try
		{
			if(!age.equals(null)){
				edad = Integer.parseInt(age);
			}
		}
		catch (NumberFormatException e)
		{
			edad = 0;
		}

		try{
			if(!movie.equals(null)){
				idPelicula = Long.parseLong(movie);
			}
		}catch (NumberFormatException e){
			idPelicula = Long.valueOf(0);
		}

			try {
				List<Personaje> list = personajeService.search(name, edad, idPelicula);

				List<PersonajeResDTO>personajes = new ArrayList<>();

				for (int i = 0; i < list.size(); i++) {

					List<PeliculaForPersonajeResDTO>peliculas = new ArrayList<>();

					for (int j = 0; j < list.get(i).getPeliculas().size(); j++) {
						peliculas.add(PeliculaForPersonajeResDTO.builder().
								titulo(list.get(i).getPeliculas().get(j).getTitulo()).
								fechaCreacion(list.get(i).getPeliculas().get(j).getFechaCreacion()).
								calificacion(list.get(i).getPeliculas().get(j).getCalificacion()).
								genero(list.get(i).getPeliculas().get(j).getGenero().getNombre()).build());
					}

					personajes.add(PersonajeResDTO.builder().
							nombre(list.get(i).getNombre()).
							edad(list.get(i).getEdad()).
							peso(list.get(i).getPeso()).
							historia(list.get(i).getHistoria()).peliculas(peliculas).build());
				}

				return new ResponseEntity(personajes, HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
			}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Personaje>> getAll() throws Exception {
		List<Personaje> list = personajeService.FindAll();
		List<ListPersonajesDTO>personajes = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			personajes.add(ListPersonajesDTO.builder().nombre(list.get(i).getNombre()).build());
		}
		return new ResponseEntity(personajes, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<PersonajeResDTO> getOne(@PathVariable("id") Long id) throws Exception {
		if(!personajeService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Personaje personaje = personajeService.FindById(id);

		List<PeliculaForPersonajeResDTO>peliculas = new ArrayList<>();

		for (int i = 0; i < personaje.getPeliculas().size(); i++) {
			peliculas.add(PeliculaForPersonajeResDTO.builder().
					titulo(personaje.getPeliculas().get(i).getTitulo()).
					fechaCreacion(personaje.getPeliculas().get(i).getFechaCreacion()).
					calificacion(personaje.getPeliculas().get(i).getCalificacion()).
					genero(personaje.getPeliculas().get(i).getGenero().getNombre()).build());
		}

		PersonajeResDTO personajeResDTO = PersonajeResDTO.builder().
				nombre(personaje.getNombre()).
				edad(personaje.getEdad()).
				peso(personaje.getPeso()).
				historia(personaje.getHistoria()).peliculas(peliculas).build();

		return new ResponseEntity(personajeResDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> save(@RequestBody PersonajeReqDTO personajeReqDTO) throws Exception {
		// Valido si se ingresa un Nombre
		if(StringUtils.isBlank(personajeReqDTO.getNombre()))
			return new ResponseEntity(new Mensaje("El Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		
		// Valido que no se repita el Nombre
		if(personajeService.existsByNombre(personajeReqDTO.getNombre()))
			return new ResponseEntity(new Mensaje("El Personaje ya existe"), HttpStatus.BAD_REQUEST);
		
		// Valido que la edad sea positiva
				if(personajeReqDTO.getEdad()<0)
					return new ResponseEntity(new Mensaje("La Edad no puede ser menor a 0"), HttpStatus.BAD_REQUEST);
		
		// Valido que el peso sea positivo
		if(personajeReqDTO.getPeso()<0)
			return new ResponseEntity(new Mensaje("El Peso no puede ser menor a 0"), HttpStatus.BAD_REQUEST);

		Personaje personaje = Mapper.MapperDTOToEntity(personajeReqDTO, Personaje.builder().build());
		personajeService.Save(personaje);
		return new ResponseEntity(new Mensaje("Personaje creado"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PersonajeReqDTO personajeReqDTO) throws Exception {
		// Valido existencia del personaje a actualizar
		if(!personajeService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		
		 //Valido que el nombre actualizado no se repita con otro existente
		if(personajeService.existsByNombre(personajeReqDTO.getNombre()) && personajeService.getByNombre(personajeReqDTO.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("El Nombre ya existe"), HttpStatus.BAD_REQUEST);
		
		// Valido que el titulo no sea vacio
		if(StringUtils.isBlank(personajeReqDTO.getNombre()))
			return new ResponseEntity(new Mensaje("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		
		// Valido que la edad sea positiva
		if(personajeReqDTO.getEdad()<0)
			return new ResponseEntity(new Mensaje("La Edad no puede ser menor a 0"), HttpStatus.BAD_REQUEST);

		// Valido que el peso sea positivo
		if(personajeReqDTO.getPeso()<0)
			return new ResponseEntity(new Mensaje("El Peso no puede ser menor a 0"), HttpStatus.BAD_REQUEST);
		
		Personaje personaje = personajeService.FindById(id);
		personaje.setNombre(personajeReqDTO.getNombre());
		personaje.setEdad(personajeReqDTO.getEdad());
		personaje.setPeso(personajeReqDTO.getPeso());
		personaje.setHistoria(personajeReqDTO.getHistoria());
		personaje.setPeliculas(personajeReqDTO.getPeliculas());
		personajeService.Update(id, personaje);
		return new ResponseEntity(new Mensaje("Personaje actualizado"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		if(!personajeService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		personajeService.Delete(id);
		return new ResponseEntity(new Mensaje("Personaje eliminado"), HttpStatus.OK);
	}
	
}
