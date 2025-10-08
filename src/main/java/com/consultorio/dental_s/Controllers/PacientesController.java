package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Pacientes;
import com.consultorio.dental_s.Mappers.PacientesMapper;
import com.consultorio.dental_s.Models.PacientesDTO;
import com.consultorio.dental_s.Services.PacientesService;
import lombok.Data;
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
@RequestMapping("api/v1/pacientes")
public class PacientesController {

    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private PacientesMapper pacientesMapper;

    @GetMapping("/findAllPacientes")
    public List<Pacientes> findAllPacientes() {
        try {
            log.info("Iniciando el controlador findAllPacientes");
            return pacientesService.findAllPacientes();
        } catch (Exception e) {
            log.info("Error al consumir el controlador --findAllPacientes-- {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/savePaciente")
    public ResponseEntity<?> savePaciente(@RequestBody PacientesDTO pacientesDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador savePaciente");
            log.info("REQUEST: {}", pacientesDTO);
            Pacientes pacienteSave = pacientesService.savePaciente(pacientesMapper.toPacientesEntity(pacientesDTO));
            if(pacienteSave != null) {
                log.info("Se inicia el almacenado del paciente");
                response.put("message","Paciente guardado con exito");
                response.put("data", pacienteSave);
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            log.info("El paciente ya existe, no se puede guardar");
            response.put("message", "El paciente ya existe");
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.error("Error al consumir el controlador --savePaciente-- {}", e.getMessage());
            response.put("message", "Error al guardar el paciente");
            response.put("description", e.getMessage());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/updatePaciente/{id}")
    public ResponseEntity<?> updatePacienteById(@PathVariable Long id, @RequestBody PacientesDTO pacientesDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador updatePaciente");
            log.info("ID PACIENTE: {}, REQUEST: {}", id, pacientesDTO);
            Pacientes pacienteUpdate = pacientesService.updatePacienteById(id, pacientesMapper.toPacientesEntity(pacientesDTO));
            if(pacienteUpdate != null) {
                log.info("Se inicia el almacenado del paciente");
                response.put("message","Paciente actualizado con exito");
                response.put("data", pacienteUpdate);
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            log.info("No se encontro el paciente con ID: {}", id);
            response.put("message", "No se encontro el paciente");
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            log.error("Error al consumir el controlador --updatePaciente-- {}", e.getMessage());
            response.put("message", "Error al actualizar el paciente");
            response.put("description", e.getMessage());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/deletePaciente/{id}")
    public ResponseEntity<?> deletePacienteById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando el controlador deletePaciente");
            log.info("ID PACIENTE: {}", id);
            boolean pacienteDelete = pacientesService.deletePacienteById(id);
            if(pacienteDelete) {
                log.info("Se elimino el paciente con exito");
                response.put("message", "Paciente eliminado con exito");
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            log.info("No se encontro el paciente con ID: {}", id);
            response.put("message", "No se encontro el paciente");
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            log.error("Error al consumir el controlador --deletePaciente-- {}", e.getMessage());
            response.put("message", "Error al eliminar el paciente");
            response.put("description", e.getMessage());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
