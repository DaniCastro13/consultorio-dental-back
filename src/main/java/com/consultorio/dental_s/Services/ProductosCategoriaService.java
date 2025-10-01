package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.ProductosCategoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductosCategoriaService {

    ProductosCategoria saveCategoria(ProductosCategoria categoria);

    List<ProductosCategoria> findAllProductosCategoria();

    ProductosCategoria updateCategoriaById(Long id, ProductosCategoria categoria);

    boolean deleteCategoriaById(Long id);
}
