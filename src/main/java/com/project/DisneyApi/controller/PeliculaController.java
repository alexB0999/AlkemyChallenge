package com.project.DisneyApi.controller;

import java.util.List;

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
import com.project.DisneyApi.dto.PeliculaDTO;
import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.service.PeliculaService;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaController {
	
	@Autowired
	PeliculaService peliculaService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Pelicula>> list(){
		List<Pelicula> list = peliculaService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Pelicula> getById(@PathVariable("id") int id){
		if(!peliculaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Pelicula pelicula = peliculaService.getOne(id).get();
		return new ResponseEntity(pelicula, HttpStatus.OK);
	}
	
	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Pelicula> getByNombre(@PathVariable("nombre") String nombre){
		if(!peliculaService.existsByTitulo(nombre)) 
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Pelicula pelicula = peliculaService.getByTitulo(nombre).get();
		return new ResponseEntity(pelicula, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody PeliculaDTO peliculaDTO){
		// Valido si se ingresa un titulo
		if(StringUtils.isBlank(peliculaDTO.getTitulo()))
			return new ResponseEntity(new Mensaje("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		// Valido que no se repita el Titulo
		if(peliculaService.existsByTitulo(peliculaDTO.getTitulo()))
			return new ResponseEntity(new Mensaje("El Titulo ya existe"), HttpStatus.BAD_REQUEST);
		// Valido que se ingrese una fecha
		if(peliculaDTO.getFechaCreacion().equals(null))
			return new ResponseEntity(new Mensaje("Por favor, ingrese una fecha de creacion"), HttpStatus.BAD_REQUEST);
		// Valido que la calificacion sea entre 1 y 5
		if((peliculaDTO.getCalificacion()<1) || (peliculaDTO.getCalificacion()>5))
			return new ResponseEntity(new Mensaje("La calificacion debe ser entre 1 y 5"), HttpStatus.BAD_REQUEST);
		Pelicula pelicula = Pelicula.builder().titulo(peliculaDTO.getTitulo()).fechaCreacion(peliculaDTO.getFechaCreacion()).
				calificacion(peliculaDTO.getCalificacion()).build();
		peliculaService.save(pelicula);
		return new ResponseEntity(new Mensaje("Pelicula creada"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PeliculaDTO peliculaDTO){
		// Valido existencia de la pelicula a actualizar
		if(!peliculaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		// Valido que el titulo actualizado no se repita con otro existente
		if(peliculaService.existsByTitulo(peliculaDTO.getTitulo()) && peliculaService.getByTitulo(peliculaDTO.getTitulo()).get().getId() != id)
			return new ResponseEntity(new Mensaje("El Titulo ya existe"), HttpStatus.BAD_REQUEST);
		// Valido que el titulo no sea vacio
		if(StringUtils.isBlank(peliculaDTO.getTitulo()))
			return new ResponseEntity(new Mensaje("El Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
		// Valido que se ingrese una fecha
		if(peliculaDTO.getFechaCreacion().equals(null))
			return new ResponseEntity(new Mensaje("Por favor, ingrese una fecha de creacion"), HttpStatus.BAD_REQUEST);
		// Valido que la calificacion sea entre 1 y 5
		if((peliculaDTO.getCalificacion()<1) || (peliculaDTO.getCalificacion()>5))
			return new ResponseEntity(new Mensaje("La calificacion debe ser entre 1 y 5"), HttpStatus.BAD_REQUEST);
		Pelicula pelicula = peliculaService.getOne(id).get();
		pelicula.setTitulo(peliculaDTO.getTitulo());
		pelicula.setFechaCreacion(peliculaDTO.getFechaCreacion());
		pelicula.setCalificacion(peliculaDTO.getCalificacion());
		pelicula.setGenero(peliculaDTO.getGenero());
		//pelicula.setPersonajes(peliculaDTO.getPersonajes());
		peliculaService.save(pelicula);
		return new ResponseEntity(new Mensaje("Pelicula actualizada"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		if(!peliculaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		peliculaService.delete(id);
		return new ResponseEntity(new Mensaje("Pelicula eliminada"), HttpStatus.OK);
	}
	

}