package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Membresias;
import com.consultorio.dental_s.Mappers.MembresiasMapper;
import com.consultorio.dental_s.Models.MembresiasDTO;
import com.consultorio.dental_s.Services.MembresiaService;
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
@RequestMapping("/api/v1/membresias")
public class MembresiasController {

    @Autowired
    private MembresiaService membresiaService;

    @Autowired
    private MembresiasMapper membresiasMapper;

    @GetMapping("/findAllMembresias")
    public List<Membresias> getAllMembresias() {
        try {
            log.info("Iniciando controlador getAllMembresias");
            return membresiaService.findAllMembresias();
        } catch (Exception e) {
            log.error("Error al consumir el controlador de buscar las membresias activas {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/saveMembresia")
    public ResponseEntity<?> saveMembresia(@RequestBody MembresiasDTO membresiasDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando controlador saveMembresia");
            log.info("REQUEST MEMBRESIA: {}", membresiasDTO);
            Membresias membresiaSave = membresiaService.saveMembresia(membresiasMapper.toMembresiaEntity(membresiasDTO));
            if(membresiaSave != null){
                response.put("message", "La membresia se almaceno con exito");
                response.put("membresia", membresiaSave);
                response.put("status", HttpStatus.CREATED.value());
                response.put("error", false);;
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("message", "La membresia ya existe registrada en el sistema");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.error("Error al consumir el controlador de guardar la membresia {}", e.getMessage());
            response.put("message", "Error al consumir el controlador de guardar la membresia");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/updateMembresia/{id}")
    public ResponseEntity<?> updateMembresiaById(@PathVariable Long id, @RequestBody MembresiasDTO membresiasDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
          log.info("Iniciando controlador updateMembresia");
          Membresias updateMembrsia = membresiaService.udateMembresiaById(id, membresiasMapper.toMembresiaEntity(membresiasDTO));
          if(updateMembrsia != null){
              response.put("message", "La membresia se actualiza con exito");
              response.put("membresia", updateMembrsia);
              response.put("status", HttpStatus.OK.value());
              response.put("error", false);
              return ResponseEntity.status(HttpStatus.OK).body(response);
          }
          response.put("message", "La membresia no se encuentra registrada en el sistema");
          response.put("status", HttpStatus.BAD_REQUEST.value());
          response.put("error", true);
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e){
            response.put("message", "Error al actualizar la membresia");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/deleteMembresia/{id}")
    public ResponseEntity<?> deleteMembresia(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando controlador deleteMembresia");
            boolean deleted = membresiaService.deleteMembresiaById(id);
            if(deleted){
                response.put("message", "La membresia se elimina con exito");
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "La membresia ya ha sido eliminado con exito");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.error("Error al eliminar la membresia {}", e.getMessage());
            response.put("message", "Error al eliminar la membresia");
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
