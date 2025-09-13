package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.MedioContacto;
import com.consultorio.dental_s.Exceptions.MedioContactoException;
import com.consultorio.dental_s.Mappers.MedioContactoMapper;
import com.consultorio.dental_s.Models.MedioContactoDTO;
import com.consultorio.dental_s.Services.MedioContactoService;
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
@RequestMapping("api/v1/medio-contacto")
public class MedioContactoController {

    @Autowired
    private MedioContactoService medioContactoService;

    @Autowired
    private MedioContactoMapper medioContactoMapper;

    @PostMapping("/saveContacto")
    public ResponseEntity<?> saveContacto(@RequestBody MedioContactoDTO medioContactoDTO) throws MedioContactoException {
        log.info("--INICIANDO EL CONTROLADOR DE ---saveConatacto---");
        Map<String, Object> body = new HashMap<>();
        MedioContacto saveCatalogo = medioContactoService.saveContacto(medioContactoMapper.toMedioContactoEntity(medioContactoDTO));
        if(saveCatalogo != null) {
            body.put("message", "Medio Contacto Almacenado con exito");
            body.put("data", medioContactoDTO);
            body.put("status", HttpStatus.CREATED.value());
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }
        body.put("message", "No se pudo crear el medio contacto");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @GetMapping("/getAllMedioContacto")
    public List<MedioContacto> findAllMedioContacto() throws  MedioContactoException {
        return medioContactoService.findAllContacto();
    }

    @PutMapping("/updateMedioContacto/{id}")
    public ResponseEntity<?> updateMedioContacto(@PathVariable Long id, @RequestBody MedioContactoDTO medioContactoDTO) throws MedioContactoException {
        log.info("--INICIANDO EL CONTROLADOR DE ---updateMedioContacto---");
        Map<String, Object> body = new HashMap<>();
        MedioContacto updateMedioContacto = medioContactoService.updateContacto(id, medioContactoMapper.toMedioContactoEntity(medioContactoDTO));
        if(updateMedioContacto != null) {
            body.put("message", "Medio Contacto Actualizado con exito");
            body.put("data", medioContactoDTO);
            body.put("status", HttpStatus.OK.value());
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        }
        body.put("message", "No se pudo actualizar el medio contacto");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @DeleteMapping("/deleteMedioContacto/{id}")
    public ResponseEntity<?> deleteMedioContacto(@PathVariable Long id) throws MedioContactoException {
        log.info("---INICIANDO EL CONTROLADOR DE ---deleteMedioContacto---");
        Map<String, Object> body = new HashMap<>();
        if(id == null) {
            body.put("message", "El id no puede venir vacio");
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
        medioContactoService.deleteContactoById(id);
        body.put("message", "Medio Contacto Eliminado con exito");
        body.put("status", HttpStatus.OK.value());
        body.put("error", false);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
