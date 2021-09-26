package com.project.DisneyApi.service;

import com.project.DisneyApi.entity.Personaje;

import java.util.List;

public interface PersonajeService extends BaseService<Personaje, Long>{

    List<Personaje> search(String name, Integer age, Long id) throws Exception;

    /*List<Personaje> searchBy(String name, Integer age) throws Exception;*/

}
