package com.project.DisneyApi.controller;

import java.io.Serializable;

import com.project.DisneyApi.dto.BaseDTO;
import com.project.DisneyApi.dto.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.DisneyApi.entity.BaseEntity;

public interface BaseController <DTO extends BaseDTO, E extends BaseEntity, ID extends Serializable> extends Mapper<DTO, E>{
	
    ResponseEntity<?> getAll() throws Exception;

    ResponseEntity<?> getAll(Pageable pageable);

    ResponseEntity<?> getOne(@PathVariable ID id) throws Exception;

    ResponseEntity<?> save(@RequestBody DTO dto) throws Exception;

    ResponseEntity<?> update(@PathVariable ID id, @RequestBody DTO dto) throws Exception;

    ResponseEntity<?> delete(@PathVariable ID id) throws Exception;

}
