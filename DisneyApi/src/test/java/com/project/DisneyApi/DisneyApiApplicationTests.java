package com.project.DisneyApi;

import com.project.DisneyApi.entity.Usuario;
import com.project.DisneyApi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DisneyApiApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	public void  crearUsuario(){
		Usuario usuario = new Usuario();
		usuario.setUsername("Alkemy");
		usuario.setPassword(bCryptPasswordEncoder.encode("Encrypted"));
		Usuario retorno = usuarioRepository.save(usuario);

		assertTrue(retorno.getPassword().equalsIgnoreCase(usuario.getPassword()));

	}

}
