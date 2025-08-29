package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Membresias;
import com.consultorio.dental_s.Models.MembresiasDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembresiasMapper {

    Membresias toMembresiaEntity(MembresiasDTO membresiasDTO);

    @InheritInverseConfiguration
    MembresiasDTO toMembresiasDTO(Membresias membresias);
}
