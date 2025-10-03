package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Productos;
import com.consultorio.dental_s.Repository.ProductosRepository;
import com.consultorio.dental_s.Services.ProductosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Transactional
    @Override
    public Productos saveProduct(Productos producto) {
        try {
            log.info("Iniciando productos save de productos");
            Optional<Productos> productExtract = productosRepository.findByProduct(producto.getCodigoBarras(), producto.getClaveProducto(), producto.getNombreProducto());
            if(!productExtract.isPresent()) {
                log.info("El producto ingresado no existe");
                return productosRepository.save(producto);
            }
            log.info("Producto ya existe registrado el sistema");
            return null;
        } catch (Exception e) {
            log.error("Error al consumir el servicio saveProduct: {}", e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Productos> findAllProducts() {
        try {
            log.info("Iniciando el servio de findAllProducts");
            List<Productos> listProductsActives = productosRepository.findAllProductsActives();
            log.info("Tamaño de la lista de productos activos: {}", listProductsActives.size());
            if(!listProductsActives.isEmpty()) {
                return listProductsActives;
            }
            log.info("La lista de productos no encontro productos activos");
            return List.of();
        } catch (Exception e) {
            log.error("Error al consumir el servicio findAllProducts: {}", e.getMessage());
            return List.of();
        }
    }

    @Transactional
    @Override
    public Productos updateProductById(Long idProduct, Productos producto) {
        try {
            if(idProduct == null) {
                log.info("El id del producto es nulo");
                return null;
            }
            return productosRepository.findById(idProduct).map((prod) -> {
                prod.setClaveProducto(producto.getClaveProducto());
                prod.setNombreProducto(producto.getNombreProducto());
                prod.setCodigoBarras(producto.getCodigoBarras());
                prod.setDescripcion(producto.getDescripcion());
                prod.setCaracteristicas(producto.getCaracteristicas());
                prod.setMaximo(producto.getMaximo());
                prod.setMinimo(producto.getMinimo());
                prod.setIndicacionesUso(producto.getIndicacionesUso());
                prod.setContraindicaciones(producto.getContraindicaciones());
                prod.setNota(producto.getNota());
                prod.setActivo(producto.getActivo());
                prod.setPrecioVenta(producto.getPrecioVenta());
                log.info("Producto actualizado con exito");
                return productosRepository.save(prod);
            }).orElseGet(() -> {
                log.info("No se encontro el producto con el id: {}", idProduct);
                return null;
            });
        } catch (Exception e) {
            log.error("Error al consumir el servicio updateProductById: {}", e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteProductById(Long idProduct) {
        try {
            if(idProduct == null || !productosRepository.findById(idProduct).isPresent()) {
                log.info("El producto ingresado no existe");
                return false;
            }
            productosRepository.deleteById(idProduct);
            log.info("Producto eliminado con exito");
            return true;
        } catch (Exception e) {
            log.error("Error al consumir el servicio deleteProductById: {}", e.getMessage());
            return false;
        }
    }
    //TODO: IMPLEMENTAR CONTROLADORES PARA CONSUMIR LOS SERVICIOS DE GUARDADO, ACTUALIZACION, ELIMINACION Y CONSULTA DE PRODUCTOS
}
