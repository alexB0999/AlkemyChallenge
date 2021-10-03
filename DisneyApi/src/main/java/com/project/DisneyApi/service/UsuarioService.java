package com.project.DisneyApi.service;

import com.project.DisneyApi.entity.Usuario;

public interface UsuarioService extends BaseService<Usuario, Long>{

    public Usuario findByUsername(String username);
    public Usuario registrar(Usuario usuario);

}
