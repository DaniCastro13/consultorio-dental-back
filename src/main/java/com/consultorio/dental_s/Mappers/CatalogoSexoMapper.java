package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoSexo;
import com.consultorio.dental_s.Models.CatalogoSexoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoSexoMapper {

    CatalogoSexo toCatalogoSexoEntity(CatalogoSexoDTO catalogoSexoDTO);

    @InheritInverseConfiguration
    CatalogoSexoDTO toCatalogoSexoDTO(CatalogoSexo catalogoSexo);
}
