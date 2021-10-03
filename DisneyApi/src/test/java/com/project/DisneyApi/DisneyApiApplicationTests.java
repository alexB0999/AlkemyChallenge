package com.project.DisneyApi;

import com.project.DisneyApi.controller.PeliculaController;
import com.project.DisneyApi.controller.PersonajeController;
import com.project.DisneyApi.dto.Mapper;
import com.project.DisneyApi.dto.PeliculaReqDTO;
import com.project.DisneyApi.dto.PersonajeReqDTO;
import com.project.DisneyApi.entity.Genero;
import com.project.DisneyApi.entity.Pelicula;
import com.project.DisneyApi.entity.Personaje;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DisneyApiApplicationTests {

	@Autowired
	private PeliculaController peliculaController;

	@Autowired
	private PersonajeController personajeController;

	Genero genero1 = new Genero("Accion");
	Genero genero2 = new Genero("SciFi");
	Genero genero3 = new Genero("Suspenso");

	@Test
	void contextLoads() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DisneyAPIPU");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(genero1);
			em.persist(genero2);
			em.persist(genero3);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
		emf.close();
	}

	@Test
	public void crearPelicula1() throws Exception {
		Date date = new Date(2010-10-30);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula1").fechaCreacion(date).calificacion(5).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void crearPelicula2() throws Exception {
		Date date = new Date(2010-01-15);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula2").fechaCreacion(date).calificacion(5).build();
		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void crearPelicula3() throws Exception {
		Date date = new Date(2010-05-20);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula3").fechaCreacion(date).calificacion(4).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void crearPelicula4() throws Exception {
		Date date = new Date(2014-10-15);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Interestellar").fechaCreacion(date).calificacion(5).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void crearPersonaje1() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Personaje1").edad(45).peso(80).
				historia("Es el protagonista de la pelicula1").build();
		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void crearPersonaje2() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Personaje2").edad(30).peso(75).
				historia("Es la antagonista de la pelicula1").build();

		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void crearPersonaje3() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Cooper").edad(45).peso(80).
				historia("Viaja por un agujero negro").build();
		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is2xxSuccessful());

	}

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
