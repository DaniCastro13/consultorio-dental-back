package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import com.consultorio.dental_s.Models.CatalogoTipoSangreDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoTipoSangreMapper {

    CatalogoTipoSangre toCatalogoTipoSangreEntity(CatalogoTipoSangreDTO catalogoTipoSangreDTO);

    @InheritInverseConfiguration
    CatalogoTipoSangreDTO toCatalogoTipoSangreDTO(CatalogoTipoSangre catalogoTipoSangre);
}
