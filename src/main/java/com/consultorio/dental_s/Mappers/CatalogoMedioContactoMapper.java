package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoMedioContacto;
import com.consultorio.dental_s.Models.CatalogoMedioContactoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoMedioContactoMapper {

    CatalogoMedioContacto toCatalogoMedioContactoEntity(CatalogoMedioContactoDTO catalogoMedioContactoDTO);

    @InheritInverseConfiguration
    CatalogoMedioContactoDTO toCatalogoMedioContactoDTO(CatalogoMedioContacto catalogoMedioContacto);

}
