package com.consultorio.dental_s.Mappers;

import com.consultorio.dental_s.Entities.ProductosCategoria;
import com.consultorio.dental_s.Models.ProductosCategoriaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductosCategoriaMapper {

    ProductosCategoria toProductosCategoria(ProductosCategoriaDTO productosCategoriaDTO);

    @InheritInverseConfiguration
    ProductosCategoriaDTO toProductosCategoriaDTO(ProductosCategoria productosCategoria);
}
