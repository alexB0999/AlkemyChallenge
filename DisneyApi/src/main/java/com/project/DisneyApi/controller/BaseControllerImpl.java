package com.project.DisneyApi.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.DisneyApi.entity.BaseEntity;
import com.project.DisneyApi.service.BaseServiceImpl;

public abstract class BaseControllerImpl <E extends BaseEntity, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long>{
	
	@Autowired
    protected S servicio;

}
