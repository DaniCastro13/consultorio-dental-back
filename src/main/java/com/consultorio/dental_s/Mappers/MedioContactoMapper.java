package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.MedioContacto;
import com.consultorio.dental_s.Models.MedioContactoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedioContactoMapper {

    MedioContacto toMedioContactoEntity(MedioContactoDTO medioContactoDTO);

    @InheritInverseConfiguration
    MedioContactoDTO toMedioContactoDTO(MedioContacto medioContacto);
}
