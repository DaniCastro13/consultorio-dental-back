package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoOpcionesHallazgos;
import com.consultorio.dental_s.Models.CatalogoOpcionesHallazgosDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoOpcionesHallazgosMapper {

    CatalogoOpcionesHallazgos toCatalogoOpcionesHallazgosEntity(CatalogoOpcionesHallazgosDTO catalogoOpcionesHallazgosDTO);

    @InheritInverseConfiguration
    CatalogoOpcionesHallazgosDTO toCatalogoOpcionesHallazgosDTO(CatalogoOpcionesHallazgos catalogoOpcionesHallazgos);
}
