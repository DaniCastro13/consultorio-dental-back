package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Productos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductosService {

    Productos saveProduct(Productos producto);

    List<Productos> findAllProducts();

    Productos updateProductById(Long idProduct, Productos producto);

    boolean deleteProductById(Long idProduct);
}
