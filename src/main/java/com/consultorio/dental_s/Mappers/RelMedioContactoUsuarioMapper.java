package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.RelMedioContactoUsuario;
import com.consultorio.dental_s.Models.RelMedioContactoUsuarioDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RelMedioContactoUsuarioMapper {

    RelMedioContactoUsuario toRelMedioContactoUsuarioEntity(RelMedioContactoUsuarioDTO relMedioContactoUsuarioDTO);

    @InheritInverseConfiguration
    RelMedioContactoUsuarioDTO toRelMedioContactoUsuarioDTO(RelMedioContactoUsuario relMedioContactoUsuario);
}
