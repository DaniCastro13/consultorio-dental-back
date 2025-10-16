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

@Slf4j
@RestController
@RequestMapping("api/v1/consultas")
public class ConsultasController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultasMapper consultasMapper;

    @GetMapping("/getAllConsultas")
    public List<Consultas> getAllConsultas() {
        try {
            List<Consultas> listConsultas = consultaService.findAllConsultas();
            log.info("Obtuve la lista de consultas {}", listConsultas.size());
            if(!listConsultas.isEmpty()) {
                return listConsultas;
            }
            log.info("La lista de consultas esta vacia");
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener la lista de consultas: {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/saveConsulta")
    public ResponseEntity<?> saveConsulta(@RequestBody ConsultasDTO consultasDTO) {
        try {
            log.info("REQUEST: {}", consultasDTO);
            Consultas consultaSave = consultaService.saveConsulta(consultasMapper.toConsultasEntity(consultasDTO));
            if(consultaSave != null) {
                log.info("Se almaceno la consulta con exito");
                return generateResponse("Consulta agendada con exito", consultaSave, HttpStatus.CREATED, false);
            }
            log.info("No se pudo almacenar la consulta");
            return generateResponse("La consulta no se pudo agendar", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            log.error("Error al guardar la consulta: {}", e.getMessage());
            return generateResponse("Error al guardar la consulta", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @PutMapping("/updateConsulta/{idConsulta}")
    public ResponseEntity<?> updateConsulta(@PathVariable Long idConsulta, @RequestBody ConsultasDTO consultasDTO) {
        try {
            log.info("ID: {}, REQUEST: {}", idConsulta, consultasDTO);
            Consultas consultaUpdate = consultaService.updateConsulta(idConsulta, consultasMapper.toConsultasEntity(consultasDTO));
            if (consultaUpdate != null) {
                log.info("La consulta se actualizo con exito");
                return generateResponse("Consulta reprogamada con exito", consultaUpdate, HttpStatus.OK, false);
            }
            log.info("No se pudo actualizar la consulta");
            return generateResponse("La consulta no se pudo reprogramar", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            log.error("Error al actualizar la consulta: {}", e.getMessage());
            return generateResponse("Error al actualizar la consulta", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @DeleteMapping("/deleteConsulta/{idConsulta}")
    public ResponseEntity<?> deleteConsulta(@PathVariable Long idConsulta) {
        try {
            log.info("ID: {}", idConsulta);
            boolean isDeleted = consultaService.deleteConsulta(idConsulta);
            if (isDeleted) {
                log.info("La consulta se elimino con exito");
                return generateResponse("Consulta eliminada con exito", null, HttpStatus.OK, false);
            } else {
                log.info("No se pudo eliminar la consulta");
                return generateResponse("La consulta no se pudo eliminar", null, HttpStatus.BAD_REQUEST, true);
            }
        } catch (Exception e) {
            log.error("Error en el controlador para eliminar la consulta: {}", e.getMessage());
            return generateResponse("Error al eliminar la consulta", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    private ResponseEntity<?> generateResponse(String message, Object description, HttpStatus status, boolean error) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("description", description);
        response.put("status", status);
        response.put("error", error);
        return ResponseEntity.status(status).body(response);
    }
}
