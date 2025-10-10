package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Citas;
import com.consultorio.dental_s.Mappers.CitasMapper;
import com.consultorio.dental_s.Models.CitasDTO;
import com.consultorio.dental_s.Services.CitasService;
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
@RequestMapping("api/v1/citas")
public class CitasController {

    @Autowired
    private CitasService citasService;

    @Autowired
    private CitasMapper citasMapper;

    @GetMapping("/getAllCitas")
    public List<Citas> getAllCitas(){
        try {
            log.info("Iniciando el controlador de obtener la lista de citas");
            List<Citas> listCitas = citasService.getAllCitas();
            if(!listCitas.isEmpty() || listCitas.size() > 0){
                log.info("Lista de citas encontrada");
                return listCitas;
            }
            return List.of();
        } catch (Exception e) {
            log.error("Error al consultar los citas {}", e.getMessage());
            return List.of();
        }
    }

     @PostMapping("/saveCita")
    public ResponseEntity<?> saveCita(@RequestBody CitasDTO citasDTO) {
        try {
            log.info("REQUEST: {}", citasDTO);
            Citas saveCita = citasService.saveCita(citasMapper.toCitasEntity(citasDTO));
            if(saveCita != null){
                log.info("Cita creada con exito");
                return generateResponse("La cita se creo con exito", saveCita, HttpStatus.CREATED, false);
            }
            log.info("La cita no se creo con exito");
            return generateResponse("No se creo la cita", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al guardar la cita {}", e.getMessage());
            return generateResponse("No se pudo guardar la cita", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @PutMapping("/updateCita/{id}")
    public ResponseEntity<?> updateCita(@PathVariable Long id, @RequestBody CitasDTO citasDTO) {
        try {
            log.info("ID: {}, REQUEST: {}", id, citasDTO);
            Citas updateCita = citasService.updateCita(id, citasMapper.toCitasEntity(citasDTO));
            if(updateCita != null){
                log.info("La cita se creo con exito");
                return generateResponse("Cita reprogramada con exito", updateCita, HttpStatus.OK, false);
            }
            log.info("La cita no se actualizo con exito");
            return generateResponse("No se pudo actualizar", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al actualizar la cita {}", e.getMessage());
            return generateResponse("Error al actualizar la cita", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @DeleteMapping("/deleteCita/{id}")
    public ResponseEntity<?> deleteCita(@PathVariable Long id) {
        try {
            log.info("ID A ELIMINAR: {}", id);
            boolean eliminarCita = citasService.deleteCita(id);
            if(eliminarCita){
                log.info("La cita se elimina con exito");
                return generateResponse("Cita eliminada con exito", null, HttpStatus.OK, false);
            }
            log.info("La cita no se elimino con exito");
            return generateResponse("No se pudo eliminar", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al eliminar la cita {}", e.getMessage());
            return generateResponse("Error al eliminar la cita", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    private ResponseEntity<?> generateResponse(String message, Object description, HttpStatus status, boolean error) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("description", description);
        response.put("status", status);
        response.put("error", error);
        return ResponseEntity.status(status).body(response);
    }
}
