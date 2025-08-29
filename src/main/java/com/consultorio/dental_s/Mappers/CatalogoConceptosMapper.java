package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoConceptos;
import com.consultorio.dental_s.Models.CatalogoConceptosDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoConceptosMapper {

    CatalogoConceptos toCatalogoConceptosEntity(CatalogoConceptosDTO catalogoConceptosDTO);

    @InheritInverseConfiguration
    CatalogoConceptosDTO toCatalogoConceptosDTO(CatalogoConceptos catalogoConceptos);
}
