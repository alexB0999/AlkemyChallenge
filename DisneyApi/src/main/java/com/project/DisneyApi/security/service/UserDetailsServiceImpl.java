package com.project.DisneyApi.security.service;

import com.project.DisneyApi.security.entity.Usuario;
import com.project.DisneyApi.security.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByUsernameOrEmail(usernameOrEmail).get();
        return UsuarioPrincipal.build(usuario);
    }
}
