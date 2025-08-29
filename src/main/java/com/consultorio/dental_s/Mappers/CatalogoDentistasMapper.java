package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import com.consultorio.dental_s.Models.CatalogoDentistasDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoDentistasMapper {

    CatalogoDentistas toCatalogoDentistasEntity(CatalogoDentistasDTO catalogoDentistasDTO);

    @InheritInverseConfiguration
    CatalogoDentistasDTO toCatalogoDentistasDTO(CatalogoDentistas catalogoDentistas);
}
