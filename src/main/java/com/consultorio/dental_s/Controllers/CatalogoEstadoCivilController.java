package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import com.consultorio.dental_s.Exceptions.CatalagoTipoSangreException;
import com.consultorio.dental_s.Exceptions.CatalogoEstadoCivilException;
import com.consultorio.dental_s.Mappers.CatalogoEstadoCivilMapper;
import com.consultorio.dental_s.Models.CatalogoEstadoCivilDTO;
import com.consultorio.dental_s.ServiceImpl.CatalogoEstadoCivilServiceImpl;
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
@RequestMapping("api/v1/catalogos")
public class CatalogoEstadoCivilController {

    @Autowired
    private CatalogoEstadoCivilServiceImpl catalogoEstadoCivilServiceImpl;

    @Autowired
    private CatalogoEstadoCivilMapper catalogoEstadoCivilMapper;

    @GetMapping("getAllCatalogosEstadoCivil")
    public List<CatalogoEstadoCivil> getAllCatalogosEstadoCivil(){
        try {
            log.info("--- INICIANDO EL CONTROLADOR getAllCatalogoEstadoCivil ---");
            log.info("--- INICIANDO CONSUMO DEL SERVICIO ---");
            List<CatalogoEstadoCivil> listCatalogoEstadoCivil = catalogoEstadoCivilServiceImpl.getCatalogoEstadoCivil();
            if(!listCatalogoEstadoCivil.isEmpty()){
                return listCatalogoEstadoCivil;
            }
            return List.of();
        } catch (CatalagoTipoSangreException exception){
            log.error("--- ERROR AL CONSUMIR getAllCatalgosEstadoCivil");
            log.error(exception.getMessage());
            return List.of();
        }
    }

    @PostMapping("/save/catalogo")
    public ResponseEntity<?> saveCatalogoEstadoCivil(@RequestBody CatalogoEstadoCivilDTO catalogoEstadoCivilDTO) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info("--- CONTROLADOR CATALOGO ESTADO CIVIL SAVE ---");
            log.info("REQUEST CATALOGO ESTADO CIVIL {} ", catalogoEstadoCivilDTO);
            CatalogoEstadoCivil catalogoEstadoCivilConvert = catalogoEstadoCivilMapper.toCatalogoEstadoCivilEntity(catalogoEstadoCivilDTO);
            log.info("--- EL DTO SE HA CONVERTIDO EN ENTIDAD ---");
            CatalogoEstadoCivil saveCatalogo = catalogoEstadoCivilServiceImpl.saveCatalogoEstadoCivil(catalogoEstadoCivilConvert);
            if( saveCatalogo != null ) {
               body.put("message", "Catalogo Estado Civil Almacenado con exito");
               body.put("catalogo", saveCatalogo);
               body.put("status", HttpStatus.CREATED);
               body.put("error", false);
               return ResponseEntity.status(HttpStatus.CREATED).body(body);
            }
            body.put("message", "No se creo el catalogo estado civil");
            body.put("status", HttpStatus.BAD_REQUEST);
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } catch (CatalagoTipoSangreException exception) {
            log.error("ERROR AL CONSUMIR EL CONTROLADOR PARA GUARDAR EL CATALOGO");
            Map<String, Object> body = new HashMap<>();
            body.put("message", exception.getMessage());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PutMapping("/updateCatalogoEstadoCivil/{id}")
    public ResponseEntity<?> updateCatalogoEstadoCivil(@PathVariable Long id, @RequestBody CatalogoEstadoCivilDTO catalogoEstadoCivilDTO) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info("REQUEST RECIBIDIO {} : ID {}: ", catalogoEstadoCivilDTO , id);
            CatalogoEstadoCivil convertEntity = catalogoEstadoCivilMapper.toCatalogoEstadoCivilEntity(catalogoEstadoCivilDTO);
            CatalogoEstadoCivil save = catalogoEstadoCivilServiceImpl.updateCatalogoEstadoCivilById(id, convertEntity);
            if( save != null ) {
                body.put("message", "Catalogo Estado Civil Actualizado con exito");
                body.put("catalogo", save);
                body.put("status", HttpStatus.OK);
                body.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }
            body.put("message", "No se creo el catalogo estado civil");
            body.put("status", HttpStatus.BAD_REQUEST);
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } catch (CatalagoTipoSangreException exception) {
            Map<String, Object> body = new HashMap<>();
            body.put("message", "Error al consumir el controlador de actualizar catalogo estado civil");
            body.put("description", exception.getMessage());
            body.put("error", true);
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @DeleteMapping("/deleteCatalogoEstadoCivil/{id}")
    public ResponseEntity<?> deleteCatalogoEstadoCivil(@PathVariable Long id) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info(" --- INICIANDO LA ELIMINACION DEL CATALOGO ESTADO CIVIL ---");
            if(id == null) {
                body.put("message", "El id es un campo obligatorio");
                body.put("error", true);
                body.put("status", HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            this.catalogoEstadoCivilServiceImpl.deleteCatalogoEstadoCivilById(id);
            body.put("message", "Catalogo Eliminado con Exito");
            body.put("status", HttpStatus.OK);
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (CatalogoEstadoCivilException exception) {
            log.info(" --- ERROR AL CONSUMIR EL ID {} ", exception.getMessage());
            Map<String, Object> body = new HashMap<>();
            body.put("message", exception.getMessage());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            body.put("error", true);
            return ResponseEntity.status(500).body(body);
        }
    }
}
