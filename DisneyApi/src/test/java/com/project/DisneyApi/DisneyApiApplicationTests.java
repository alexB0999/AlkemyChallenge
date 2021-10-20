package com.project.DisneyApi;

import com.project.DisneyApi.controller.PeliculaController;
import com.project.DisneyApi.controller.PersonajeController;
import com.project.DisneyApi.dto.Mapper;
import com.project.DisneyApi.dto.PeliculaReqDTO;
import com.project.DisneyApi.dto.PersonajeReqDTO;
import com.project.DisneyApi.entity.Genero;
import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.security.controller.AuthController;
import com.project.DisneyApi.security.dto.JwtDTO;
import com.project.DisneyApi.security.dto.LoginUsuario;
import com.project.DisneyApi.security.dto.NuevoUsuario;
import com.project.DisneyApi.security.entity.Rol;
import com.project.DisneyApi.security.enums.RolNombre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DisneyApiApplicationTests {

	@Autowired
	private PeliculaController peliculaController;

	@Autowired
	private PersonajeController personajeController;

	@Autowired
	private AuthController authController;

	Genero genero1 = new Genero("Accion");
	Genero genero2 = new Genero("SciFi");
	Genero genero3 = new Genero("Suspenso");
	Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
	Rol rolUser = new Rol(RolNombre.ROLE_USER);

	@Test
	void contextLoads() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DisneyAPIPU");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(genero1);
			em.persist(genero2);
			em.persist(genero3);
			em.persist(rolAdmin);
			em.persist(rolUser);
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
	public void crearPersonaje4() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Personaje4").edad(15).peso(50).
				historia("El es un joven").build();
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
	public void  registrarUsuario() throws IOException {
		NuevoUsuario nuevoUsuario = NuevoUsuario.builder().username("Alkemy").password("1234")
				.email("max00m_e139b@gexik.com").roles("ADMIN").build();
		ResponseEntity usuarioTest = authController.signin(nuevoUsuario);

		assertTrue(usuarioTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void loginUsuario() throws Exception {
	//La primera ejecucion puede fallar por ejecutarse antes que registrarUsuario() y no haber datos cargados
		LoginUsuario loginUsuario = new LoginUsuario("Alkemy","1234");
		JwtDTO jwtDTO = authController.login(loginUsuario);

		assertTrue(!jwtDTO.getToken().isEmpty());

	}*/

	@Test
	public void  borrarPelicula1() throws Exception {

		Long id = Long.valueOf(1);
		ResponseEntity peliculaTest = peliculaController.delete(id);

		assertTrue(peliculaTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void borrarPersonaje4() throws Exception {

		Long id = Long.valueOf(4);
		ResponseEntity personajeTest = personajeController.delete(id);

		assertTrue(personajeTest.getStatusCode().is2xxSuccessful());

	}

	@Test
	public void crearPersonajeSinNombre() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().edad(15).peso(50).
				historia("El es un joven").build();
		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is4xxClientError());
	}

	@Test
	public void crearPersonajeConNombreRepetido() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Cooper").edad(45).peso(80).
				historia("Viaja por un agujero negro").build();
		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is4xxClientError());
	}

	@Test
	public void crearPersonajeConEdadNegativa() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Personaje de Prueba").edad(-15).peso(50).
				historia("El es un joven").build();
		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is4xxClientError());
	}

	@Test
	public void crearPersonajeConPesoNegativo() throws Exception {
		PersonajeReqDTO personajeReqDTO = PersonajeReqDTO.builder().nombre("Personaje de Prueba").edad(15).peso(-50).
				historia("El es un joven").build();
		ResponseEntity personajeTest = personajeController.save(personajeReqDTO);

		assertTrue(personajeTest.getStatusCode().is4xxClientError());
	}

	@Test
	public void crearPeliculaSinTitulo() throws Exception {

		Date date = new Date(2010-10-30);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().fechaCreacion(date).calificacion(5).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is4xxClientError());

	}

	@Test
	public void crearPeliculaConTituloRepetido() throws Exception {

		Date date = new Date(2010-01-15);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula2")
				.fechaCreacion(date).calificacion(5).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is4xxClientError());

	}

	@Test
	public void crearPeliculaSinFecha() throws Exception {

		Date date = new Date();
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula de Prueba").fechaCreacion(date).calificacion(5).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is4xxClientError());

	}

	@Test
	public void crearPeliculaConCalificacionMenorA1() throws Exception {

		Date date = new Date(2010-10-30);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula de Prueba")
				.fechaCreacion(date).calificacion(0).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is4xxClientError());

	}

	@Test
	public void crearPeliculaConCalificacionMayorA5() throws Exception {

		Date date = new Date(2010-10-30);
		PeliculaReqDTO peliculaReqDTO = PeliculaReqDTO.builder().titulo("Pelicula de Prueba")
				.fechaCreacion(date).calificacion(7).build();

		ResponseEntity peliculaTest = peliculaController.save(peliculaReqDTO);

		assertTrue(peliculaTest.getStatusCode().is4xxClientError());

	}
}
