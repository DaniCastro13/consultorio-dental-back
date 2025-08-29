package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Gastos;
import com.consultorio.dental_s.Models.GastosDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GastosMapper {

    Gastos toGastosEntity(GastosDTO gastosDTO);

    @InheritInverseConfiguration
    GastosDTO toGastosDTO(Gastos gastos);
}
