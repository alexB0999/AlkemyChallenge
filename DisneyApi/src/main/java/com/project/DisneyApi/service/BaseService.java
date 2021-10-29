package com.project.DisneyApi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.DisneyApi.entity.BaseEntity;

public interface BaseService <E extends BaseEntity, ID extends Serializable>{
	
    List<E> FindAll() throws Exception;

    Page<E> FindAll(Pageable pageable) throws Exception;

    E FindById(ID id) throws Exception;

    E Save(E entity) throws Exception;

    E Update(ID id, E entity) throws Exception;

    boolean Delete(ID id) throws Exception;

}

