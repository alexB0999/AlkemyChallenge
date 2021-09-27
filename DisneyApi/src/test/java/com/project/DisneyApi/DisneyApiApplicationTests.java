package com.project.DisneyApi;

import com.project.DisneyApi.dto.Mapper;
import com.project.DisneyApi.dto.PersonajeReqDTO;
import com.project.DisneyApi.entity.Genero;
import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.repository.PeliculaRepository;
import com.project.DisneyApi.repository.PersonajeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DisneyApiApplicationTests {

	/*@Autowired
	private UsuarioRepository usuarioRepository;*/

	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	@Autowired
	private PeliculaRepository peliculaRepository;

	@Autowired
	private PersonajeRepository personajeRepository;

	@Test
	void contextLoads() {
	}

	/*@Test
	public void crearPelicula(){
		Date date = new Date(2014-10-15);
		Genero genero = new Genero("Accion");
		Pelicula pelicula = Pelicula.builder().titulo("Interestellar").fechaCreacion(date).calificacion(5).genero(genero).build();
		Pelicula peliculaTest = peliculaRepository.save(pelicula);

		assertTrue(peliculaTest.getTitulo().equals(pelicula.getTitulo()));
		assertTrue(peliculaTest.getFechaCreacion().equals(pelicula.getFechaCreacion()));
		assertTrue(peliculaTest.getCalificacion() == pelicula.getCalificacion());
		assertTrue(peliculaTest.getGenero().equals(pelicula.getGenero()));

	}

	@Test
	public void crearPersonaje(){
		Personaje personaje =  Personaje.builder().nombre("Cooper").edad(45).peso(80).historia("Viaja por un agujero negro").build();
		Personaje personajeTest = personajeRepository.save(personaje);

		assertTrue(personajeTest.getNombre().equals(personaje.getNombre()));
		assertTrue(personajeTest.getEdad() == personaje.getEdad());
		assertTrue(personajeTest.getPeso() == personaje.getPeso());
		assertTrue(personajeTest.getHistoria().equals(personaje.getHistoria()));
	}*/

	@Test
	public  void EntityToDTO(){
		PersonajeReqDTO personajeReqDTO = new PersonajeReqDTO();
		Personaje personaje =  Personaje.builder().nombre("Cooper").edad(45).peso(80).
				historia("Viaja por un agujero negro").build();
		personajeReqDTO = Mapper.MapperEntityToDTO(personaje, personajeReqDTO);

		assertTrue(personaje.getNombre().equals(personajeReqDTO.getNombre()));
		assertTrue(personaje.getEdad()==personajeReqDTO.getEdad());
		assertTrue(personaje.getPeso()==personajeReqDTO.getPeso());
		assertTrue(personaje.getHistoria().equals(personajeReqDTO.getHistoria()));

	}

	@Test
	public  void DTOToEntity(){
		Personaje personaje = new Personaje();
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Cooper").edad(45).peso(80).
				historia("Viaja por un agujero negro").build();
		personaje = Mapper.MapperDTOToEntity(personajeReqDTO, personaje);

		assertTrue(personajeReqDTO.getNombre().equals(personaje.getNombre()));
		assertTrue(personajeReqDTO.getEdad()==personaje.getEdad());
		assertTrue(personajeReqDTO.getPeso()==personaje.getPeso());
		assertTrue(personajeReqDTO.getHistoria().equals(personaje.getHistoria()));

	}

	/*@Test
	public void  crearUsuario(){
		Usuario usuario = new Usuario();
		usuario.setUsername("Alkemy");
		usuario.setPassword(bCryptPasswordEncoder.encode("Encrypted"));
		Usuario retorno = usuarioRepository.save(usuario);

		assertTrue(retorno.getPassword().equalsIgnoreCase(usuario.getPassword()));

	}*/

}
