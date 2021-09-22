package com.project.DisneyApi.service;

import com.project.DisneyApi.entity.Personaje;

import java.util.List;

public interface PersonajeService extends BaseService<Personaje, Long>{

    List<Personaje> searchNombre(String name) throws Exception;

    List<Personaje> searchEdad(String age) throws Exception;

}
