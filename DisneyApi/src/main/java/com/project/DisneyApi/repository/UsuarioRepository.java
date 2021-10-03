package com.project.DisneyApi.repository;

import com.project.DisneyApi.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long>{

    public Usuario findByUsername(String username);

}
