package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Citas;
import com.consultorio.dental_s.Models.CitasDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitasMapper {

    Citas toCitasEntity(CitasDTO citasDTO);

    @InheritInverseConfiguration
    CitasDTO toCitasDTO(Citas citas);
}
