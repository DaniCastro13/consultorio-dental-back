package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.CatalogoRoles;
import com.consultorio.dental_s.Models.CatalogoRolesDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoRolesMapper {

    CatalogoRoles toCatalogoRolesEntity(CatalogoRolesDTO catalogoRolesDTO);

    @InheritInverseConfiguration
    CatalogoRolesDTO toCatalogoRolesDTO(CatalogoRoles catalogoRoles);
}
