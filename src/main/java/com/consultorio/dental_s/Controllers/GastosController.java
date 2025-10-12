package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Gastos;
import com.consultorio.dental_s.Mappers.GastosMapper;
import com.consultorio.dental_s.Models.GastosDTO;
import com.consultorio.dental_s.Services.GastosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/gastos")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    @Autowired
    private GastosMapper gastosMapper;

    @GetMapping("/getAllGastos")
    public List<Gastos> getAllGastos(){
        try {
            log.info("Iniciando el controlador para obtener la lista de gastos");
            List<Gastos> listGastos = gastosService.getAllGastos();
            log.info("Lista de gastos obtenida con éxito, total de gastos: {}", listGastos.size());
            if(listGastos.size()>0){
                return listGastos;
            } else {
                log.info("No se encontraron gastos en la base de datos");
                return List.of();
            }
        } catch (Exception e) {
            log.error("Error al obtener la lista de gastos: {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/saveGasto")
    public ResponseEntity<?> saveGasto(@RequestBody GastosDTO gastosDTO){
        try {
            log.info("Iniciando el controlador para guardar un gasto");
            log.info("REQUEST: {}", gastosDTO);
            Gastos saveGasto = gastosService.saveGasto(gastosMapper.toGastosEntity(gastosDTO));
            if(saveGasto!=null){
                log.info("Gasto almacenado con éxito");
                return generateResponse("Gasto almacenado con éxito", saveGasto, HttpStatus.CREATED, false);
            }
            log.warn("No se pudo almacenar el gasto");
            return generateResponse("No se pudo almacenar el gasto", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            log.error("Error al guardar el gasto: {}", e.getMessage());
            return generateResponse("Error al guardar el gasto", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @PutMapping("/updateGasto/{idGasto}")
    public ResponseEntity<?> updateGastoById(@PathVariable Long idGasto, @RequestBody GastosDTO gastosDTO){
        try {
            log.info("Iniciando el controlador para actualizar el gasto con ID: {}, REQUEST: {}", idGasto, gastosDTO);
            Gastos updateGasto = gastosService.updateGastoById(idGasto, gastosMapper.toGastosEntity(gastosDTO));
            if(updateGasto!=null){
                log.info("Gasto actualizado con éxito");
                return generateResponse("Gasto actualizado con éxito", updateGasto, HttpStatus.OK, false);
            }
            log.info("No se pudo actualizar el gasto");
            return generateResponse("No se pudo actualizar el gasto", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            log.error("Error al actualizar el gasto: {}", e.getMessage());
            return generateResponse("Error al actualizar el gasto", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @DeleteMapping("/deleteGasto/{idGasto}")
    public ResponseEntity<?> deleteGastoById(@PathVariable Long idGasto) {
        try {
            log.info("Iniciando el controlador para eliminar el gasto con ID: {}", idGasto);
            boolean deleteGasto = gastosService.deleteGastoById(idGasto);
            if(deleteGasto){
                log.info("Se eliminó el gasto con éxito");
                return generateResponse("El gasto se elimino con exito", null, HttpStatus.OK, false);
            }
            log.info("No se pudo eliminar el gasto");
            return generateResponse("No se pudo eliminar el gasto", "El gasto ya no existe en el sistema", HttpStatus.NOT_FOUND, false);
        } catch (Exception e) {
            log.error("Error al eliminar el gasto: {}", e.getMessage());
            return generateResponse("Error al eliminar el gasto", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    private ResponseEntity<?> generateResponse(String message, Object description, HttpStatus status, boolean error) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("description", description);
        response.put("status", status.value());
        response.put("error", error);
        return ResponseEntity.status(status).body(response);
    }
}
