package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Pacientes;
import com.consultorio.dental_s.Models.PacientesDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacientesMapper {

    Pacientes toPacientesEntity(PacientesDTO pacientesDTO);

    @InheritInverseConfiguration
    PacientesDTO toPacientesDTO(Pacientes pacientes);
}
