package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import com.consultorio.dental_s.Exceptions.CatalagoTipoSangreException;
import com.consultorio.dental_s.Mappers.CatalogoTipoSangreMapper;
import com.consultorio.dental_s.Models.CatalogoTipoSangreDTO;
import com.consultorio.dental_s.ServiceImpl.CatalogoTipoSangreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/catalogo-tipo-sangre")
public class CatalogoTipoSangreController {

    @Autowired
    private CatalogoTipoSangreServiceImpl catalogoTipoSangreService;

    @Autowired
    private CatalogoTipoSangreMapper catalogoTipoSangreMapper;

    @PostMapping("/create-tipo-sangre")
    public ResponseEntity<?> createTipoSangre(@RequestBody CatalogoTipoSangreDTO catalogoTipoSangreDTO) {
        try {
            log.info("CREATE TIPO SANGRE: Iniciando Controlador");
            log.info("Request: {}", catalogoTipoSangreDTO);
            CatalogoTipoSangre convertedEntity = catalogoTipoSangreMapper.toCatalogoTipoSangreEntity(catalogoTipoSangreDTO);
            CatalogoTipoSangre savedEntity = catalogoTipoSangreService.saveCatalogoTipoSangre(convertedEntity);
            if(savedEntity!=null){
                Map<String, Object> body = new HashMap<>();
                body.put("message", "Tipo de sangre creado exitosamente");
                body.put("request", savedEntity);
                body.put("status", HttpStatus.CREATED.value());
                return ResponseEntity.status(HttpStatus.CREATED).body(body);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el tipo de sangre");
        } catch (CatalagoTipoSangreException ex) {
            log.info("No se pudo crear el tipo de sangre: {}", ex.getMessage());
            Map<String, Object> body = new HashMap<>();
            body.put("message", ex.getMessage());
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }
}
