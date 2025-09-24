package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Entities.*;
import com.consultorio.dental_s.Exceptions.*;
import com.consultorio.dental_s.Mappers.*;
import com.consultorio.dental_s.Models.*;
import com.consultorio.dental_s.ServiceImpl.CatalogoEstadoCivilServiceImpl;
import com.consultorio.dental_s.ServiceImpl.CatalogoSexoServiceImpl;
import com.consultorio.dental_s.ServiceImpl.CatalogoTipoSangreServiceImpl;
import com.consultorio.dental_s.Services.CatalogoDentistasService;
import com.consultorio.dental_s.Services.CatalogoMedioContactoService;
import com.consultorio.dental_s.Services.CatalogoPromocionesService;
import com.consultorio.dental_s.Services.CatalogoRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/catalogos")
public class CatalogosController {

    @Autowired
    private CatalogoEstadoCivilServiceImpl catalogoEstadoCivilServiceImpl;

    @Autowired
    private CatalogoEstadoCivilMapper catalogoEstadoCivilMapper;

    @Autowired
    private CatalogoTipoSangreServiceImpl catalogoTipoSangreService;

    @Autowired
    private CatalogoTipoSangreMapper catalogoTipoSangreMapper;

    @Autowired
    private CatalogoSexoServiceImpl catalogoSexoServiceImpl;

    @Autowired
    private CatalogoSexoMapper catalogoSexoMapper;

    @Autowired
    private CatalogoMedioContactoService catalogoMedioContactoService;

    @Autowired
    private CatalogoMedioContactoMapper catalogoMedioContactoMapper;

    @Autowired
    private CatalogoDentistasService catalogoDentistasService;

    @Autowired
    private CatalogoDentistasMapper catalogoDentistasMapper;

    @Autowired
    private CatalogoRolesService catalogoRolesService;

    @Autowired
    private CatalogoRolesMapper catalogoRolesMapper;

    @Autowired
    private CatalogoPromocionesService catalogoPromocionesService;

    @Autowired
    private CatalogoPromocionesMapper catalogoPromocionesMapper;

    @PostMapping("/save/catalogo-sexo")
    public ResponseEntity<?> saveCatalogoSexo(@RequestBody CatalogoSexoDTO catalogoSexoDTO) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info("--- INICIANDO CONTROLADOR SAVECATALOGO ---");
            CatalogoSexo saveCatalogoSexo = catalogoSexoServiceImpl.saveCatalogoSexo(catalogoSexoMapper.toCatalogoSexoEntity(catalogoSexoDTO));
            if (saveCatalogoSexo != null) {
                body.put("message", "Catalogo Sexo almacenado exitosamente");
                body.put("catalog", catalogoSexoDTO);
                body.put("error", false);
                body.put("status", HttpStatus.CREATED);
                return ResponseEntity.status(HttpStatus.CREATED).body(body);
            }
            body.put("message", "No se pudo almacenar el catalogo");
            body.put("description", "El catalogo ya exitse");
            body.put("error", true);
            body.put("status", HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("--- ERROR EN EL CONTROLADOR DE CATALOGO DE SEXO ---");
            log.error(catalogoSexoException.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catalogoSexoException);
        }
    }

    @GetMapping("/getAllCatalogoSexo")
    public List<CatalogoSexo> getAllCatalogoSexo() {
        try {
            log.info("--- INICIANDO CONTROLADOR PARA OBTENER LOS CATALOGOS SEXO ---");
            return catalogoSexoServiceImpl.getAllCatalogoSexo();
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("ERROR AL CONSUMIR EL CONTROLADOR ");
            return List.of();
        }
    }

    @PutMapping("/updateCatalogoSexo/{id}")
    public ResponseEntity<?> updateCatalogoSexo(@PathVariable Long id, @RequestBody CatalogoSexoDTO catalogoSexoDTO) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info("--- CATALOGO SEXO: updateCatalogo ---: {}", catalogoSexoDTO);
            CatalogoSexo catSexo = catalogoSexoServiceImpl.updateCatalogoSexoById(id, catalogoSexoMapper.toCatalogoSexoEntity(catalogoSexoDTO));
            if (catSexo != null) {
                body.put("message", "Catalogo Sexo actualizado exitosamente");
                body.put("catalog", catalogoSexoDTO);
                body.put("error", false);
                body.put("status", HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }
            body.put("message", "No se pudo almacenar el catalogo");
            body.put("error", true);
            body.put("status", HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("ERROR AL CONSUMIR EL CONTROLADOR CATALOGO UPDATE");
            log.info(catalogoSexoException.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catalogoSexoException);
        }
    }

    @DeleteMapping("/deleteCatalogoSexo/{id}")
    public ResponseEntity<?> deleteCatalogoSexo(@PathVariable Long id) {
        try {
            Map<String, Object> body = new HashMap<>();
            if (id == null) {
                body.put("message", "El id del catalogo no puede ser nulo");
                body.put("error", true);
                body.put("status", HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            catalogoSexoServiceImpl.deleteCatalogoById(id);
            body.put("message", "Catalogo Eliminado exitosamente");
            body.put("error", false);
            body.put("status", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("--- ERROR AL CONSUMIR EL CONTROLADOR PARA ELIMINAR EL CATALOGO SEXO ---");
            log.info("message: {}", catalogoSexoException.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catalogoSexoException);
        }
    }

    @GetMapping("/getAllCatalogosEstadoCivil")
    public List<CatalogoEstadoCivil> getAllCatalogosEstadoCivil() {
        try {
            log.info("--- INICIANDO EL CONTROLADOR getAllCatalogoEstadoCivil ---");
            log.info("--- INICIANDO CONSUMO DEL SERVICIO ---");
            List<CatalogoEstadoCivil> listCatalogoEstadoCivil = catalogoEstadoCivilServiceImpl.getCatalogoEstadoCivil();
            if (!listCatalogoEstadoCivil.isEmpty()) {
                return listCatalogoEstadoCivil;
            }
            return List.of();
        } catch (CatalogoEstadoCivilException exception) {
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
            if (saveCatalogo != null) {
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
            log.info("REQUEST RECIBIDIO {} : ID {}: ", catalogoEstadoCivilDTO, id);
            CatalogoEstadoCivil convertEntity = catalogoEstadoCivilMapper.toCatalogoEstadoCivilEntity(catalogoEstadoCivilDTO);
            CatalogoEstadoCivil save = catalogoEstadoCivilServiceImpl.updateCatalogoEstadoCivilById(id, convertEntity);
            if (save != null) {
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
            if (id == null) {
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

    @PostMapping("/create-tipo-sangre")
    public ResponseEntity<?> createTipoSangre(@RequestBody CatalogoTipoSangreDTO catalogoTipoSangreDTO) {
        try {
            log.info("CREATE TIPO SANGRE: Iniciando Controlador");
            log.info("Request: {}", catalogoTipoSangreDTO);
            CatalogoTipoSangre convertedEntity = catalogoTipoSangreMapper.toCatalogoTipoSangreEntity(catalogoTipoSangreDTO);
            CatalogoTipoSangre savedEntity = catalogoTipoSangreService.saveCatalogoTipoSangre(convertedEntity);
            if (savedEntity != null) {
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

    @GetMapping("/getAllCatalogoTipoSangre")
    public List<CatalogoEstadoCivil> getAllCatalogoTipoSangre() {
        try {
            log.info("GETALL TIPO SANGRE: Iniciando Controlador");
            return this.catalogoEstadoCivilServiceImpl.getCatalogoEstadoCivil();
        } catch (Exception e) {
            log.info("GETALL TIPO SANGRE: {}", e.getMessage());
            return List.of();
        }
    }

    @PutMapping("/updateEstadoCivil/{id}")
    public ResponseEntity<?> updateEstadoCivil(@PathVariable Long id, @RequestBody CatalogoTipoSangreDTO catalogoTipoSangreDTO) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info("INICIANDO EL CONTROLADOR PARA ACTUALIZAR EL ESTADO CIVIL");
            CatalogoTipoSangre saveCatalogo = catalogoTipoSangreService.updateCatalogoTipoSangre(id, catalogoTipoSangreMapper.toCatalogoTipoSangreEntity(catalogoTipoSangreDTO));
            if (saveCatalogo != null) {
                body.put("message", "Catalogo Actualizado con Exito");
                body.put("status", HttpStatus.OK);
                body.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }
            body.put("message", "Estado civil no actualizado");
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } catch (CatalagoTipoSangreException exception) {
            log.error("Error al consumir el controlador del updateEstadoCivil");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @DeleteMapping("/deleteEstadoCivil/{id}")
    public ResponseEntity<?> deleteCatalogoTipoSangre(@PathVariable Long id) {
        try {
            Map<String, Object> body = new HashMap<>();
            log.info("Iniciando el controlador para eliminar el estado civil");
            if (id == null) {
                body.put("message", "El id es un campo obligatorio");
                body.put("status", HttpStatus.BAD_REQUEST);
                body.put("error", true);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            log.info("El estado civil se elimino exitosamente");
            body.put("message", "El estado civil se eliminado");
            body.put("status", HttpStatus.OK);
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (CatalagoTipoSangreException ex) {
            log.error("Error al eliminar el estado civil {} ", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/saveMedioContacto")
    public ResponseEntity<?> saveMedioContacto(@RequestBody CatalogoMedioContactoDTO catalogoMedioContactoDTO) throws CatalagoTipoSangreException {
        Map<String, Object> body = new HashMap<>();
        log.info("Iniciando controlador ---saveMedioContacto---");
        log.info("CatalogoMedioContactoDTO: {}", catalogoMedioContactoDTO);
        CatalogoMedioContacto saveMedio = catalogoMedioContactoService.saveMedioContacto(catalogoMedioContactoMapper.toCatalogoMedioContactoEntity(catalogoMedioContactoDTO));
        if (saveMedio != null) {
            body.put("message", "Medio Contacto Creado con Exito");
            body.put("medioContatco", catalogoMedioContactoDTO);
            body.put("status", HttpStatus.CREATED);
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }
        throw new CatalogoMedioContactoException("No se pudo almacenar el medio contacto");
    }

    @GetMapping("/getAllMediosContactos")
    public List<CatalogoMedioContacto> getAllMediosContactos() {
        return catalogoMedioContactoService.findAllMedioContacto();
    }

    @PutMapping("/updateMedioContacto/{id}")
    public ResponseEntity<?> updateMedioContacto(@PathVariable Long id, @RequestBody CatalogoMedioContactoDTO catalogoMedioContactoDTO) throws CatalagoTipoSangreException {
        log.info("Iniciando controlador ---updateMedioContacto---");
        Map<String, Object> body = new HashMap<>();
        if (id == null) {
            throw new CatalogoMedioContactoException("El id es un campo obligatorio");
        }
        CatalogoMedioContacto updateMedioContact = catalogoMedioContactoService.updatemMedioContacto(id, catalogoMedioContactoMapper.toCatalogoMedioContactoEntity(catalogoMedioContactoDTO));
        if (updateMedioContact != null) {
            body.put("message", "Medio Contacto actualizado con exito");
            body.put("medioContatco", catalogoMedioContactoDTO);
            body.put("status", HttpStatus.OK);
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        }
        body.put("message", "Estado civil no actualizado");
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @DeleteMapping("/deleteMedioContacto/{id}")
    public ResponseEntity<?> deleteMedioContacto(@PathVariable Long id) throws CatalagoTipoSangreException {
        log.info("Iniciando controlador ---deleteMedioContacto---");
        Map<String, Object> body = new HashMap<>();
        if (id == null) {
            throw new CatalogoMedioContactoException("El id no puede ser nulo");
        }
        this.catalogoMedioContactoService.deleteMedioContacto(id);
        body.put("message", "Medio Contacto eliminado con Exito");
        body.put("status", HttpStatus.OK);
        body.put("error", false);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/findAllRoles")
    public List<CatalogoRoles> getAllRoles() {
        try {
            List<CatalogoRoles> listRoles = catalogoRolesService.findAllRoles();
            if (!listRoles.isEmpty()) {
                log.info("Obtuve la siguiente lista {}", listRoles);
                return listRoles;
            }
            log.info("No obtuve ninguna lista");
            return listRoles;
        } catch (Exception ex) {
            log.error("Error al consumir el controlador de findAllRoles {}", ex.getMessage());
            return List.of();
        }
    }

    @PostMapping("/save/rol")
    public ResponseEntity<?> saveRol(@RequestBody CatalogoRolesDTO catalogoRolesDTO) {
        try {
            log.info("Iniciando el controlador para guardar el rol");
            log.info("REQUEST RECIBIDO: {}", catalogoRolesDTO);
            Map<String, Object> response = new HashMap<>();
            CatalogoRoles saveRol = catalogoRolesService.saveRol(catalogoRolesMapper.toCatalogoRolesEntity(catalogoRolesDTO));
            if (saveRol != null) {
                response.put("rol", catalogoRolesDTO);
                response.put("message", "Rol almacenado con exito");
                response.put("error", false);
                response.put("status", HttpStatus.CREATED.value());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response.put("message", "No se pudo almacenar el rol");
            response.put("error", true);
            response.put("status", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception ex) {
            log.error("No se pudo consumir el controlador de saveRol {}", ex.getMessage());
            Map<String, Object> body = new HashMap<>();
            body.put("message", "Error al guardar rol");
            body.put("description", ex.getMessage());
            body.put("error", true);
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PutMapping("/updateRol/{id}")
    public ResponseEntity<?> updateRolById(@PathVariable Long id, @RequestBody CatalogoRolesDTO rolesDTO) {
        try {
            Map<String, Object> response = new HashMap<>();
            log.info("Iniciando el controlador de actualizar el rol");
            log.info("REQUEST recibida: {}", rolesDTO);
            CatalogoRoles rolUpdate = catalogoRolesService.updateRolById(id, catalogoRolesMapper.toCatalogoRolesEntity(rolesDTO));
            if (rolUpdate != null) {
                log.info("Rol actualizado con exito");
                response.put("message", "Rol actualizado con exito");
                response.put("rol", rolesDTO);
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "No se pudo actualizar el rol");
            response.put("error", true);
            response.put("status", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception ex) {
            Map<String, Object> body = new HashMap<>();
            log.error("Error al actualizar el rol");
            log.info("Error description {}", ex.getMessage());
            body.put("message", "Error al consumir el controlador de actualizar el rol");
            body.put("descripcion", ex.getMessage());
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @DeleteMapping("/deleteRol/{id}")
    public ResponseEntity<?> deleteRolById(@PathVariable Long id) {
        try {
            Map<String, Object> response = new HashMap<>();
            boolean isDeleted = catalogoRolesService.deleteRolById(id);
            if (isDeleted) {
                response.put("message", "El rol se elimino con exito");
                response.put("error", false);
                response.put("status", HttpStatus.OK.value());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "No se pudo eliminar el rol");
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, Object> body = new HashMap<>();
            body.put("message", "Error al consumir el servicio de eliminar el rol");
            body.put("description", e.getMessage());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @GetMapping("/findAllDentistas")
    public List<CatalogoDentistas> findAllDentistas() {
        try {
            return catalogoDentistasService.findAllDentistas();
        } catch (Exception e) {
            log.error("Error al consumir el controlador de findAllDentistas {}", e.getMessage());
            return List.of();
        }
    }


    @PostMapping("/saveDentista")
    public ResponseEntity<?> saveDentista(@RequestBody CatalogoDentistasDTO catalogoDentistasDTO) throws CatalogoDentistasException {
        log.info("Iniciando controlador ---saveDentista---");
        log.info("REQUEST RECIBIDO: {}", catalogoDentistasDTO);
        Map<String, Object> body = new HashMap<>();
        CatalogoDentistas saveDentista = catalogoDentistasService.saveDetista(catalogoDentistasMapper.toCatalogoDentistasEntity(catalogoDentistasDTO));
        if (saveDentista != null) {
            body.put("message", "Dentista Creado con exito");
            body.put("dentista", catalogoDentistasDTO);
            body.put("status", HttpStatus.CREATED.value());
            body.put("error", false);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }
        throw new CatalogoDentistasException("No se pudo almacenar el dentista");
    }

    @PutMapping("/updateDentista/{id}")
    public ResponseEntity<?> updateDentista(@PathVariable Long id, @RequestBody CatalogoDentistasDTO catalogoDentistasDTO) {
        Map<String, Object> body = new HashMap<>();
        try {
            log.info("Iniciando controlador ---updateDentista---");
            log.info("REQUEST recibida: {}", catalogoDentistasDTO);
            CatalogoDentistas updateDentista = catalogoDentistasService.updateDentista(id, catalogoDentistasMapper.toCatalogoDentistasEntity(catalogoDentistasDTO));
            if (updateDentista != null) {
                body.put("message", "Dentista actualizado con exito");
                body.put("dentista", catalogoDentistasDTO);
                body.put("status", HttpStatus.OK.value());
                body.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }
            body.put("message", "No se pudo actualizar el dentista");
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } catch (Exception e) {
            log.error("Error al consumir el controlador de updateDentista {}", e.getMessage());
            body.put("message", "Error al consumir el controlador de actualizar el dentista");
            body.put("description", e.getMessage());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @DeleteMapping("/deleteDentista/{id}")
    public ResponseEntity<?> deleteDentista(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean dentistaDeleted = catalogoDentistasService.deleteDentista(id);
            if (dentistaDeleted) {
                response.put("message", "El dentista se elimino con exito");
                response.put("error", false);
                response.put("status", HttpStatus.OK.value());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("message", "No se pudo eliminar el dentista");
                response.put("error", true);
                response.put("status", HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            log.error("Error al consumir el controlador de deleteDentista {}", e.getMessage());
            response.put("message", "Error al consumir el servicio de eliminar el dentista");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/promociones/getAllPromociones")
    public List<CatalogoPromociones>  getAllPromociones() {
        try {
            log.info("Iniciando controlador ---getAllPromociones---");
            return catalogoPromocionesService.listaPromociones();
        } catch (Exception e) {
            log.error("Error al obtener los promociones {}", e.getMessage());
            return List.of();
        }
    }

    @PostMapping("/promociones/savePromocion")
    public ResponseEntity<?> savePromocion(@RequestBody CatalogoPromocionesDTO catalogoPromocionesDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando controlador ---savePromocion---");
            log.info("REQUEST CatalogoPromocionesDTO: {}", catalogoPromocionesDTO);
            CatalogoPromociones savePromo = catalogoPromocionesService.savePromocion(catalogoPromocionesMapper.toCatalogoPromocionesEntity(catalogoPromocionesDTO));
            if (savePromo != null) {
                response.put("message", "Promocion creada con exito");
                response.put("data", catalogoPromocionesDTO);
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "No se pudo actualizar el promocion");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.error("Error al obtener los promociones {}", e.getMessage());
            response.put("message", "Error al obtener los promociones");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/promociones/updatePromocion/{id}")
    public ResponseEntity<?> updatePromocion(@PathVariable Long id, @RequestBody CatalogoPromocionesDTO promocionesDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Iniciando controlador ---updatePromocion---");
            log.info("REQUEST CatalogoPromocionesDTO: {}", promocionesDTO);
            CatalogoPromociones updatePromocion = catalogoPromocionesService.updatePromocionById(id, catalogoPromocionesMapper.toCatalogoPromocionesEntity(promocionesDTO));
            if (updatePromocion != null) {
                response.put("message", "Promocion actualizado con exito");
                response.put("data", promocionesDTO);
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            log.info("No se pudo actualizar el promocion {}", id);
            response.put("message", "No se pudo actualizar el promocion");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch(Exception ex) {
            log.error("Error al actualizar el promocion {}", ex.getMessage());
            response.put("message", "Error al actualizar el promocion");
            response.put("description", ex.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/promociones/deletePromocion/{id}")
    public ResponseEntity<?> deletePromocion(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean promocionDelete = catalogoPromocionesService.deletePromocionById(id);
            if (promocionDelete) {
                response.put("message", "Promocion eliminado con exito");
                response.put("status", HttpStatus.OK.value());
                response.put("error", false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "El producto que se intenta eliminar no existe registrao en el sistema");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            log.error("Error al eliminar el promocion {}", e.getMessage());
            response.put("message", "Error al eliminar el promocion");
            response.put("description", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
