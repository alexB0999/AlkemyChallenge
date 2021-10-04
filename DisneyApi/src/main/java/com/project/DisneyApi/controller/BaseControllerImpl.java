package com.project.DisneyApi.controller;

import com.project.DisneyApi.dto.BaseDTO;
import com.project.DisneyApi.dto.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.DisneyApi.entity.BaseEntity;
import com.project.DisneyApi.serviceImpl.BaseServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseControllerImpl <DTO extends BaseDTO, E extends BaseEntity, S extends BaseServiceImpl<E, Long>> implements BaseController<DTO, E, Long> {

	E entity;

    @Autowired
    protected S servicio;

    //Documentacion en: http://localhost:8080/swagger-ui/index.html (Puede requerir token)

    @Operation(summary = "Este metodo devuelve una lista de objetos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "trae una lista de objetos del Controller correspondiente",
                    content = {@Content}),
            @ApiResponse(responseCode = "404",
                    description = "el controlador no se encuentra, o no es posible comunicarse con la base de datos",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.FindAll());
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mÃ¡s tarde.\"}");
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.FindAll(pageable));
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
    @Operation(summary = "Este metodo devuelve un objeto", parameters = @Parameter(name = "id", in = ParameterIn.PATH, required = true, schema = @Schema(type = "Long")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "trae un objeto del Controller correspondiente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "el controlador no se encuentra, o no es posible comunicarse con la base de datos",
                    content = @Content)

    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.FindById(id));
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }


    @Operation(summary = "Este metodo guarda un objeto", parameters = @Parameter(name = "entity", in = ParameterIn.PATH, required = true, schema = @Schema(type = "Object")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "guarda un objeto en la base de datos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "el controlador no se encuentra, o no es posible comunicarse con la base de datos",
                    content = @Content)

    })

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DTO dto) throws Exception {
        E entidad = Mapper.MapperDTOToEntity(dto, this.entity);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.Save(entidad));
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @Operation(summary = "Este metodo recibe un id y actualiza el objeto asociado", parameters = @Parameter(name = "id", in = ParameterIn.PATH, required = true, schema = @Schema(type = "Long")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "actualiza un objeto en la base de datos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "el controlador no se encuentra, o no es posible comunicarse con la base de datos",
                    content = @Content)

    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DTO dto) throws Exception {
        E entidad = Mapper.MapperDTOToEntity(dto, this.entity);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.Update(id, entidad));
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @Operation(summary = "Este metodo recibe un id y elimina el objeto asociado", parameters = @Parameter(name = "id", in = ParameterIn.PATH, required = true, schema = @Schema(type = "Long")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "elimina un objeto en la base de datos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "el controlador no se encuentra, o no es posible comunicarse con la base de datos",
                    content = @Content)

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.Delete(id));
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }


}
