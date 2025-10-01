package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.ProductosCategoria;
import com.consultorio.dental_s.Repository.ProductosCategoriaRepository;
import com.consultorio.dental_s.Services.ProductosCategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductosCategoriaServiceImpl implements ProductosCategoriaService {

    @Autowired
    private ProductosCategoriaRepository productosCategoriaRepository;

    @Transactional
    @Override
    public ProductosCategoria saveCategoria(ProductosCategoria categoria) {
        try {
            log.info("Iniciando el servicio de guardar producto categoria");
            Optional<ProductosCategoria> clave = productosCategoriaRepository.findProductosCategoriaByClave(categoria.getClaveCategoria());
            if (clave.isPresent()) {
                log.info("Esta categoria del producto ya existe");
                return null;
            }
            log.info("El producto categoria no existe, comienza el guardado");
            return productosCategoriaRepository.save(categoria);
        } catch (Exception e) {
            log.info("Error al guardar el producto categoria {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductosCategoria> findAllProductosCategoria() {
        try {
            log.info("Iniciando el servicio de listar productos categoria");
            List<ProductosCategoria> listProductsCategory = productosCategoriaRepository.findProductosCategoriaByActivo();
            if(!listProductsCategory.isEmpty()) {
                log.info("Obtuve la lista de los productos categoria activos {}", listProductsCategory);
                return listProductsCategory;
            }
            log.info("No extraje la informacion con categorias de productos activos");
            return List.of();
        } catch (Exception e) {
            log.info("Error al consumir el servicio de buscar productos categoria");
            return List.of();
        }
    }

    @Override
    public ProductosCategoria updateCategoriaById(Long id, ProductosCategoria categoria) {
        try {
            log.info("Iniciando el servicio de actualizar producto categoria");
            return productosCategoriaRepository.findById(id).map((cat) -> {
                cat.setClaveCategoria(categoria.getClaveCategoria());
                cat.setNombreCategoria(categoria.getNombreCategoria());
                cat.setDescripcionCategoria(categoria.getDescripcionCategoria());
                cat.setActivo(categoria.getActivo());
                return productosCategoriaRepository.save(cat);
            }).orElseThrow(() -> {
                log.info("No se encontro el producto categoria {}", id);
                return null;
            });
        } catch (Exception e) {
            log.info("Error al actualizar el producto categoria {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteCategoriaById(Long id) {
        try {
            log.info("Iniciando el servicio para eliminar producto categoria");
            Optional<ProductosCategoria> productosCategoria = productosCategoriaRepository.findById(id);
            if (id == null || productosCategoria.isEmpty()) {
                log.info("El id del producto categoria no puede ser null");
                return false;
            }
            log.info("Eliminando el producto categoria {}", id);
            productosCategoriaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.info("Error al eliminar el producto categoria {}", e.getMessage());
            return false;
        }
    }
}
