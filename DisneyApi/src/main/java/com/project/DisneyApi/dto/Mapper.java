package com.project.DisneyApi.dto;

import com.google.gson.Gson;
import com.project.DisneyApi.entity.BaseEntity;

public interface Mapper <DTO extends BaseDTO, E extends BaseEntity>{

    public static <DTO,E> E MapperDTOToEntity(DTO fromDto, E toEntity){

        String aux_dto = new Gson().toJson(fromDto);

        toEntity = (E) new Gson().fromJson(aux_dto, toEntity.getClass());

        return toEntity;
    }

    public static <DTO, E> DTO MapperEntityToDTO(E fromEntity, DTO toDto){

        String aux_entity = new Gson().toJson(fromEntity);

        toDto = (DTO)new Gson().fromJson(aux_entity, toDto.getClass());

        return toDto;
    }


}
