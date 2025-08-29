package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Hallazgos;
import com.consultorio.dental_s.Models.HallazgosDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HallazgosMapper {

    Hallazgos toHallazgosEntity(HallazgosDTO hallazgosDTO);

    @InheritInverseConfiguration
    HallazgosDTO toHallazgosDTO(Hallazgos hallazgos);

    // Mapeo de LISTAS
    List<Hallazgos> toHallazgosEntityList(List<HallazgosDTO> hallazgosDTOList);

    List<HallazgosDTO> toHallazgosDTOList(List<Hallazgos> hallazgosList);
}
