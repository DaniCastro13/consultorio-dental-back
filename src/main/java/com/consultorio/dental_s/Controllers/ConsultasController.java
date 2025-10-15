package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Consultas;
import com.consultorio.dental_s.Mappers.ConsultasMapper;
import com.consultorio.dental_s.Models.ConsultasDTO;
import com.consultorio.dental_s.Services.ConsultaService;
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
@RequestMapping("/api/v1/consultas")
public class ConsultasController {

    @Autowired
    ConsultaService consultaService;

    @Autowired
    ConsultasMapper consultasMapper;

    @GetMapping("/getAllConsultas")
    public List<Consultas> getAllConsultas() {
        try {
            log.info("Iniciando el controlador getAllConsultas");
            List<Consultas> listConsult = consultaService.getAllConsultas();
            log.info("Obtuve la lista de consultas {}", listConsult);
            if(!listConsult.isEmpty() || listConsult.size()>0) {
                return listConsult;
            }
            return List.of();
        } catch (Exception ex) {
            log.error("Error al obtener la lista de consultas {}", ex.getMessage());
            return List.of();
        }
    }

    @PostMapping("/saveConsulta")
    public ResponseEntity<?> saveConsulta(@RequestBody ConsultasDTO consultasDTO) {
        try {
            log.info("Iniciando el controlador saveConsulta");
            log.info("REQUEST: {}", consultasDTO);
            Consultas saveConsulta = consultaService.saveConsulta(consultasMapper.toConsultasEntity(consultasDTO));
            if (saveConsulta != null) {
                return generatedResponse("Consulta agendada con exito", saveConsulta, HttpStatus.CREATED, false);
            }
            log.info("No se pudo almacenar la consulta");
            return generatedResponse("No se pudo almacenar la consulta", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al guardar la consulta {}", e.getMessage());
            return generatedResponse("Error al guardar la consulta", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @PutMapping("/updateConsulta/{idConsulta}")
    public ResponseEntity<?> updateConsulta(@PathVariable Long idConsulta, @RequestBody ConsultasDTO consultasDTO) {
        try {
            log.info("Iniciando el controlador updateConsulta");
            log.info("REQUEST: {}", consultasDTO);
            Consultas updateConsulta = consultaService.updateConsulta(idConsulta, consultasMapper.toConsultasEntity(consultasDTO));
            if (updateConsulta != null) {
                return generatedResponse("Consulta actualizada", updateConsulta, HttpStatus.OK, false);
            }
            log.info("No se pudo actualizar la consulta");
            return generatedResponse("No se pudo actualizar la consulta", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al actualizar la consulta {}", e.getMessage());
            return generatedResponse("Error al actualizar la consulta", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @DeleteMapping("/deleteConsulta/{idConsulta}")
    public ResponseEntity<?> deleteConsulta(@PathVariable Long idConsulta) {
        try {
            log.info("Iniciando el controlador deleteConsulta");
            log.info("REQUEST: {}", idConsulta);
            if(consultaService.deleteConsulta(idConsulta)) {
                return generatedResponse("Consulta eliminada", null, HttpStatus.OK, false);
            }
            log.info("No se pudo eliminar la consulta");
            return generatedResponse("No se pudo eliminar la consulta", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al eliminar la consulta {}", e.getMessage());
            return generatedResponse("Error al eliminar la consulta", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false);
        }
    }

    private ResponseEntity<?> generatedResponse(String message, Object description, HttpStatus status, boolean error) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("description", description);
        response.put("status", status);
        response.put("error", error);
        return ResponseEntity.status(status).body(response);
    }
}
