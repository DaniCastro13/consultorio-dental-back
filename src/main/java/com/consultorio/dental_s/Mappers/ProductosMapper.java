package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.Productos;
import com.consultorio.dental_s.Models.ProductosDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductosMapper {

    Productos toProductosEntity(ProductosDTO productosDTO);

    @InheritInverseConfiguration
    ProductosDTO toProductosDTO(Productos productos);
}
