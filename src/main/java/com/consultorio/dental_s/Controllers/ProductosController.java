package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Productos;
import com.consultorio.dental_s.Mappers.ProductosMapper;
import com.consultorio.dental_s.Models.ProductosDTO;
import com.consultorio.dental_s.Services.ProductosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductosMapper productosMapper;

    @GetMapping("/findAllProducts")
    public List<Productos> getAllProducts() {
        try {
            log.info("Iniciando el controlador de getAllProducts");
            return productosService.findAllProducts();
        } catch (Exception e) {
            log.error("Error al consumir el controlador de getAllProducts {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/saveProducts")
    public ResponseEntity<?> saveProduct(@RequestBody ProductosDTO productosDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador de saveProduct");
            log.info("REQUEST PRODUCT: {}", productosDTO);
            Productos productSave = productosService.saveProduct(productosMapper.toProductosEntity(productosDTO));
            if (productSave != null) {
                return generateResponse("El producto se almaceno con exito", productosDTO, HttpStatus.CREATED, false);
            }
            log.info("El producto ya se encuentra almacenado en el sistema");
            return generateResponse("El producto ya se encuentra registrado", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            return generateResponse("Error al guardar el producto", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductosDTO productosDTO) {
        try {
            log.info("Iniciando el controlador de updateProduct");
            log.info("REQUEST PRODUCT: {}", productosDTO);
            Productos productUpdate = productosService.updateProductById(id, productosMapper.toProductosEntity(productosDTO));
            if (productUpdate != null) {
                return generateResponse("El producto se actualizo con exito", productosDTO, HttpStatus.OK, false);
            }
            return generateResponse("El producto no existe", null, HttpStatus.NOT_FOUND, true);
        } catch (Exception e) {
            return generateResponse("Error al actualizar el producto", e.getMessage(), HttpStatus.BAD_REQUEST, true);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            log.info("Iniciando el controlador de deleteProduct");
            boolean deleted = productosService.deleteProductById(id);
            if (deleted) {
                return generateResponse("El producto se eliminado", null, HttpStatus.OK, false);
            }
            return generateResponse("El producto no existe", null, HttpStatus.NOT_FOUND, true);
        } catch (Exception e) {
          log.error("Error al eliminar el producto {}", e.getMessage());
          return generateResponse("Error al eliminar el producto", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    private ResponseEntity<?> generateResponse(String message, Object descritption, HttpStatus status, boolean error) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("descritption", descritption);
        response.put("status", status);
        response.put("error", error);
        return ResponseEntity.status(status).body(response);
    }
}
