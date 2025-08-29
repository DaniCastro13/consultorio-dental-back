package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Auxiliar;
import com.consultorio.dental_s.Models.AuxiliarDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuxiliarMapper {

    Auxiliar toAuxiliarEntity(AuxiliarDTO auxiliarDTO);

    @InheritInverseConfiguration
    AuxiliarDTO toAuxiliarDTO(Auxiliar auxiliar);
}
