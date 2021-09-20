package com.project.DisneyApi.repository;

import com.project.DisneyApi.entity.Personaje;
import com.project.DisneyApi.entity.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario, Long>{

    Usuario findByUsername (String username);
}
