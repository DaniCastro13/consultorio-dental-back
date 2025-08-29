package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoProcedimientos;
import com.consultorio.dental_s.Models.CatalogoProcedimientosDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogoProcedimientosMapper {

    CatalogoProcedimientos toCatalogoProcedimientosEntity(CatalogoProcedimientosDTO catalogoProcedimientosDTO);

    @InheritInverseConfiguration
    CatalogoProcedimientosDTO toCatalogoProcedimientosDTO(CatalogoProcedimientos catalogoProcedimientos);

    List<CatalogoProcedimientosDTO> toCatalogoProcedimientosDTOList(List<CatalogoProcedimientos> procedimientosList);
    List<CatalogoProcedimientos> toCatalogoProcedimientosEntityList(List<CatalogoProcedimientosDTO> procedimientosDTOList);
}
