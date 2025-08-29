package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import com.consultorio.dental_s.Models.CatalogoEstadoCivilDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoEstadoCivilMapper {

    CatalogoEstadoCivil toCatalogoEstadoCivilEntity(CatalogoEstadoCivilDTO catalogoEstadoCivilDTO);

    @InheritInverseConfiguration
    CatalogoEstadoCivilDTO toCatalogoEstadoCivilDTO(CatalogoEstadoCivil catalogoEstadoCivil);
}
