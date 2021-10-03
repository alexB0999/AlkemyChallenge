package com.project.DisneyApi.service;

import com.project.DisneyApi.entity.Pelicula;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PeliculaService extends BaseService<Pelicula, Long>{

    List<Pelicula> search(String name, Long id, String sort) throws Exception;

}
