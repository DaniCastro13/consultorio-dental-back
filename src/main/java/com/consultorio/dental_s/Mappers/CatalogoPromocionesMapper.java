package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoPromociones;
import com.consultorio.dental_s.Models.CatalogoPromocionesDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoPromocionesMapper {

    CatalogoPromociones toCatalogoPromocionesEntity(CatalogoPromocionesDTO catalogoPromocionesDTO);

    @InheritInverseConfiguration
    CatalogoPromocionesDTO toCatalogoPromocionesDTO(CatalogoPromociones catalogoPromociones);
}
