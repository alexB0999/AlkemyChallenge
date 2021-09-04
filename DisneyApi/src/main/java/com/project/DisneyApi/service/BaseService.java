package com.project.DisneyApi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.DisneyApi.entity.BaseEntity;

public interface BaseService <E extends BaseEntity, ID extends Serializable>{
	
    public List<E> FindAll() throws Exception;

    public Page<E> FindAll(Pageable pageable) throws Exception;

    public E FindById(ID id) throws Exception;

    public E Save(E entity) throws Exception;

    public E Update(ID id, E entity) throws Exception;

    public boolean Delete(ID id) throws Exception;

}

