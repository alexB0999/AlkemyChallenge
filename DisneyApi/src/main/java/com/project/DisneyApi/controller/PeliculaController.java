package com.project.DisneyApi.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.DisneyApi.dto.*;
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

import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.service.PeliculaServiceImpl;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaController {
	
	@Autowired
	PeliculaServiceImpl peliculaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Pelicula>> list(){
		List<Pelicula> list = peliculaService.list();
		ListPeliculasDTO peliculasDTOS = new ListPeliculasDTO();
		List<ListPeliculasDTO>peliculas = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			peliculasDTOS.setTitulo(list.get(i).getTitulo());
			peliculasDTOS.setFechaCreacion(list.get(i).getFechaCreacion());
			peliculas.add(peliculasDTOS);
		}
		return new ResponseEntity(peliculas, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Pelicula> getById(@PathVariable("id") Long id){
		if(!peliculaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Pelicula pelicula = peliculaService.getOne(id).get();
		PeliculaResDTO peliculaResDTO = new PeliculaResDTO();
		peliculaResDTO.setTitulo(pelicula.getTitulo());
		peliculaResDTO.setFechaCreacion(pelicula.getFechaCreacion());
		peliculaResDTO.setCalificacion(pelicula.getCalificacion());
		peliculaResDTO.setGenero(pelicula.getGenero().getNombre());

		PersonajeForPeliculaResDTO personajeForPeliculaResDTO = new PersonajeForPeliculaResDTO();

		List<PersonajeForPeliculaResDTO>personajes = new ArrayList<>();

		for (int i = 0; i < pelicula.getPersonajes().size(); i++) {
			personajeForPeliculaResDTO.setNombre(pelicula.getPersonajes().get(i).getNombre());
			personajeForPeliculaResDTO.setEdad(pelicula.getPersonajes().get(i).getEdad());
			personajeForPeliculaResDTO.setPeso(pelicula.getPersonajes().get(i).getPeso());
			personajeForPeliculaResDTO.setHistoria(pelicula.getPersonajes().get(i).getHistoria());
			personajes.add(personajeForPeliculaResDTO);
		}
		peliculaResDTO.setPersonajes(personajes);
		return new ResponseEntity(peliculaResDTO, HttpStatus.OK);
	}
	
	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Pelicula> getByNombre(@PathVariable("nombre") String nombre){
		if(!peliculaService.existsByTitulo(nombre)) 
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Pelicula pelicula = peliculaService.getByTitulo(nombre).get();

		PeliculaResDTO peliculaResDTO = new PeliculaResDTO();

		peliculaResDTO.setTitulo(pelicula.getTitulo());
		peliculaResDTO.setFechaCreacion(pelicula.getFechaCreacion());
		peliculaResDTO.setCalificacion(pelicula.getCalificacion());
		peliculaResDTO.setGenero(pelicula.getGenero().getNombre());

		PersonajeForPeliculaResDTO personajeForPeliculaResDTO = new PersonajeForPeliculaResDTO();

		List<PersonajeForPeliculaResDTO>personajes = new ArrayList<>();

		for (int i = 0; i < pelicula.getPersonajes().size(); i++) {
			personajeForPeliculaResDTO.setNombre(pelicula.getPersonajes().get(i).getNombre());
			personajeForPeliculaResDTO.setEdad(pelicula.getPersonajes().get(i).getEdad());
			personajeForPeliculaResDTO.setPeso(pelicula.getPersonajes().get(i).getPeso());
			personajeForPeliculaResDTO.setHistoria(pelicula.getPersonajes().get(i).getHistoria());
			personajes.add(personajeForPeliculaResDTO);

		}
		peliculaResDTO.setPersonajes(personajes);
		return new ResponseEntity(peliculaResDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody PeliculaReqDTO peliculaReqDTO){
		// Valido si se ingresa un titulo
		if(StringUtils.isBlank(peliculaReqDTO.getTitulo()))
			return new ResponseEntity(new Mensaje("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		// Valido que no se repita el Titulo
		if(peliculaService.existsByTitulo(peliculaReqDTO.getTitulo()))
			return new ResponseEntity(new Mensaje("El Titulo ya existe"), HttpStatus.BAD_REQUEST);
		// Valido que se ingrese una fecha
		if(peliculaReqDTO.getFechaCreacion().equals(null))
			return new ResponseEntity(new Mensaje("Por favor, ingrese una fecha de creacion"), HttpStatus.BAD_REQUEST);
		// Valido que la calificacion sea entre 1 y 5
		if((peliculaReqDTO.getCalificacion()<1) || (peliculaReqDTO.getCalificacion()>5))
			return new ResponseEntity(new Mensaje("La calificacion debe ser entre 1 y 5"), HttpStatus.BAD_REQUEST);
		Pelicula pelicula = Pelicula.builder().titulo(peliculaReqDTO.getTitulo()).fechaCreacion(peliculaReqDTO.getFechaCreacion()).
				calificacion(peliculaReqDTO.getCalificacion()).build();
		peliculaService.save(pelicula);
		return new ResponseEntity(new Mensaje("Pelicula creada"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PeliculaReqDTO peliculaReqDTO){
		// Valido existencia de la pelicula a actualizar
		if(!peliculaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		// Valido que el titulo actualizado no se repita con otro existente
		if(peliculaService.existsByTitulo(peliculaReqDTO.getTitulo()) && peliculaService.getByTitulo(peliculaReqDTO.getTitulo()).get().getId() != id)
			return new ResponseEntity(new Mensaje("El Titulo ya existe"), HttpStatus.BAD_REQUEST);
		// Valido que el titulo no sea vacio
		if(StringUtils.isBlank(peliculaReqDTO.getTitulo()))
			return new ResponseEntity(new Mensaje("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		// Valido que se ingrese una fecha
		if(peliculaReqDTO.getFechaCreacion().equals(null))
			return new ResponseEntity(new Mensaje("Por favor, ingrese una fecha de creacion"), HttpStatus.BAD_REQUEST);
		// Valido que la calificacion sea entre 1 y 5
		if((peliculaReqDTO.getCalificacion()<1) || (peliculaReqDTO.getCalificacion()>5))
			return new ResponseEntity(new Mensaje("La calificacion debe ser entre 1 y 5"), HttpStatus.BAD_REQUEST);

		Pelicula pelicula = peliculaService.getOne(id).get();
		pelicula.setTitulo(peliculaReqDTO.getTitulo());
		pelicula.setFechaCreacion(peliculaReqDTO.getFechaCreacion());
		pelicula.setCalificacion(peliculaReqDTO.getCalificacion());
		pelicula.setGenero(peliculaReqDTO.getGenero());
		pelicula.setPersonajes(peliculaReqDTO.getPersonajes());

		peliculaService.save(pelicula);
		return new ResponseEntity(new Mensaje("Pelicula actualizada"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(!peliculaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		peliculaService.delete(id);
		return new ResponseEntity(new Mensaje("Pelicula eliminada"), HttpStatus.OK);
	}
	

}