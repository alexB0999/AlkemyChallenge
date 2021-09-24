package com.project.DisneyApi.service;

import com.project.DisneyApi.entity.Pelicula;

import java.util.List;

public interface PeliculaService extends BaseService<Pelicula, Long>{

    List<Pelicula> search(String name) throws Exception;

}
