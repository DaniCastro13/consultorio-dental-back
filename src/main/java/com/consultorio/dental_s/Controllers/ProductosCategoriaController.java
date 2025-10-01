package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.ProductosCategoria;
import com.consultorio.dental_s.Mappers.ProductosCategoriaMapper;
import com.consultorio.dental_s.Models.ProductosCategoriaDTO;
import com.consultorio.dental_s.Services.ProductosCategoriaService;
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
@RequestMapping("api/v1/productos/categoria")
public class ProductosCategoriaController {

    @Autowired
    private ProductosCategoriaService productosCategoriaService;

    @Autowired
    private ProductosCategoriaMapper productosCategoriaMapper;

    @GetMapping("/getAllCategoria")
    public List<ProductosCategoria> findAllCategoria() {
        try {
            log.info("Iniciando el controlador de findAllCategoria");
            return productosCategoriaService.findAllProductosCategoria();
        } catch (Exception e) {
            log.info("Error al obtener los productos categoria");
            return List.of();
        }
    }

    @PostMapping("/save/categoria")
    public ResponseEntity<?> saveCategoria(@RequestBody ProductosCategoriaDTO productosCategoriaDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador de saveCategoria");
            log.info("REQUEST: Productos categoria {}", productosCategoriaDTO);
            ProductosCategoria categoriaSave = productosCategoriaService.saveCategoria(productosCategoriaMapper.toProductosCategoria(productosCategoriaDTO));
            if(categoriaSave != null) {
                response.put("request", productosCategoriaDTO);
                response.put("message", "La categoria del producto se ha almacenado correctamente");
                response.put("status", HttpStatus.CREATED.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("message", "No se ha podido salvar el productos categoria");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.info("Error al obtener el productos categoria {}", e.getMessage());
            response.put("message", "Error al obtener el productos categoria");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/categoria/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Long id, @RequestBody ProductosCategoriaDTO productosCategoriaDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador de updateCategoria");
            log.info("REQUEST: Id: {}, Productos categoria {}", id,productosCategoriaDTO);
            ProductosCategoria updateCategoria = productosCategoriaService.updateCategoriaById(id, productosCategoriaMapper.toProductosCategoria(productosCategoriaDTO));
            if(updateCategoria != null) {
                response.put("request", productosCategoriaDTO);
                response.put("message", "La categoria del producto se ha almacenado correctamente");
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "No se ha podido actualizar el productos categoria");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.info("Error al consumir el controlador de actualizar el producto categoria {}", e.getMessage());
            response.put("message", "Error al obtener el productos categoria");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/categoria/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador de deleteCategoria");
            boolean isDeleted = productosCategoriaService.deleteCategoriaById(id);
            if(isDeleted) {
                response.put("request", id);
                response.put("message", "Productos categoria eliminado correctamente");
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "No se ha podido eliminar el productos categoria");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.info("Error al eliminar el productos categoria {}", e.getMessage());
            response.put("message", "Error al eliminar el productos categoria");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
