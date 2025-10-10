package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.Hallazgos;
import com.consultorio.dental_s.Mappers.HallazgosMapper;
import com.consultorio.dental_s.Models.HallazgosDTO;
import com.consultorio.dental_s.Services.HallazgosService;
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
@RequestMapping("api/v1/hallazgos")
public class HallazgosControlles {

    @Autowired
    private HallazgosService hallazgosService;

    @Autowired
    private HallazgosMapper hallazgosMapper;

    @GetMapping("/findAllHallazgos")
    public List<Hallazgos> getAllHallazgos(){
        try {
            log.info("Iniciando el controlador para obtener hallazgos");
            List<Hallazgos> listHallazgos = hallazgosService.findAllHallazgos();
            log.info("Lista hallazgos {}", listHallazgos);
            if(!listHallazgos.isEmpty() || listHallazgos.size() > 0){
                return listHallazgos;
            }
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener la lista hallazgos {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/saveHallazgos")
    public ResponseEntity<?> saveHallazgos(@RequestBody HallazgosDTO hallazgosDTO){
        try {
            log.info("Iniciando el controlador para save hallazgos");
            log.info("REQUEST HALLAZGOS: {}", hallazgosDTO);
            Hallazgos hallazgoSave = hallazgosService.saveHallazgo(hallazgosMapper.toHallazgosEntity(hallazgosDTO));
            if(hallazgoSave != null){
                log.info("Se almaceno con exito el hallazgos");
                return generatedResponse("Hallazgos almacenado con exito", hallazgoSave, HttpStatus.CREATED, false);
            }
            return generatedResponse("No se pudo almacenar el hallazgos", null, HttpStatus.BAD_REQUEST, false);
        } catch (Exception e) {
            log.error("Error al consumir el controlador saveHallazgos {}", e.getMessage());
            return generatedResponse("No se pudo almacenar el hallazgs", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @PutMapping("/updateHallazgo/{id}")
    public ResponseEntity<?> updateHallazgoByID(@PathVariable Long id, @RequestBody HallazgosDTO hallazgosDTO){
        try {
            if(id == null) {
                log.info("El id no puede ser null");
                return generatedResponse("El id no puede ser null", "Seleccione el hallazgo a actualizar", HttpStatus.BAD_REQUEST, false);
            }
            log.info("Iniciando el controlador para updateHallazgo");
            log.info("REQUEST HALLAZGOS ID: {}, HallazgoDTO: {}", id, hallazgosDTO);
            Hallazgos updateHallago = hallazgosService.updateHallazgo(id, hallazgosMapper.toHallazgosEntity(hallazgosDTO));
            if(updateHallago != null){
                log.info("Se almaceno con exito el hallazgo");
                return generatedResponse("Hallazgos actualizado con exito", updateHallago, HttpStatus.OK, false);
            }
            log.info("No se pudo almacenar el hallazgos");
            return generatedResponse("No se pudo actualizar el hallazgos", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            log.error("Error al actualizar el hallazgos {}", e.getMessage());
            return generatedResponse("No se pudo actualizar el hallazgos", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    @DeleteMapping("/deleteHallazgo/{id}")
    public ResponseEntity<?> deleteHallazgoByID(@PathVariable Long id){
        try {
            if(id == null) {
                log.info("El id no puede ser null");
                return generatedResponse("El id no puede ser null", "Seleccione el hallazgo a eliminar", HttpStatus.BAD_REQUEST, false);
            }
            log.info("Iniciando el controlador para deleteHallazgo");
            boolean result = hallazgosService.deleteHallazgo(id);
            if(result){
               return generatedResponse("Hallazgos eliminado", null, HttpStatus.OK, true);
            }
            return generatedResponse("No se pudo eliminar el hallazgos", null, HttpStatus.BAD_REQUEST, true);
        } catch (Exception e) {
            log.error("Error al eliminar el hallazgos {}", e.getMessage());
            return generatedResponse("Error al eliminar el hallazgos", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, true);
        }
    }

    private ResponseEntity<?> generatedResponse(String message, Object description, HttpStatus httpStatus, boolean error) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("description", description);
        response.put("status", httpStatus);
        response.put("error", error);
        return ResponseEntity.status(httpStatus).body(response);
    }
}
