package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoRoles;
import com.consultorio.dental_s.Repository.CatalogoRolesRepository;
import com.consultorio.dental_s.Services.CatalogoRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoRolesServiceImpl implements CatalogoRolesService {

    @Autowired
    private CatalogoRolesRepository catalogoRolesRepository;

    @Transactional
    @Override
    public CatalogoRoles saveRol(CatalogoRoles rol) {
        try {
            Optional<CatalogoRoles> claveRol = catalogoRolesRepository.findBYClave(rol.getClaveRol());
            if(claveRol.isPresent()) {
                log.info("La clave ya existe: {}", rol.getClaveRol());
                return null;
            }
            log.info("No existe la clave, se almacena clave con exito");
            return catalogoRolesRepository.save(rol);
        } catch (Exception ex) {
            log.info("Error al consumir el servicio saveRol: {}", ex.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CatalogoRoles> findAllRoles() {
        try {
            log.info("Iniciando el servivio findAllRoles");
            List<CatalogoRoles> lisRolesActives = catalogoRolesRepository.findByAllActive();
            if(lisRolesActives.isEmpty()) {
                log.info("La lista esta vacia");
                return List.of();
            }
            log.info("Lista obtenida {}", lisRolesActives);
            return lisRolesActives;
        } catch (Exception ex) {
            log.error("Error al consumir el servicio de busquedad roles");
            return List.of();
        }
    }

    @Transactional
    @Override
    public CatalogoRoles updateRolById(Long id, CatalogoRoles rol) {
        try {
            if(id == null ) {
                log.info("El id no puede venir nulo");
                return null;
            }
            return catalogoRolesRepository.findById(id).map((rolUpdate) -> {
                log.info("Iniciando la actualizacion del rol {}", rol.getNombreRol());
                rolUpdate.setClaveRol(rol.getClaveRol());
                rolUpdate.setNombreRol(rol.getNombreRol());
                rolUpdate.setActivo(rol.getActivo());
                return catalogoRolesRepository.save(rolUpdate);
            }).orElseThrow(() -> {
                log.info("No se encontro el rol con el id proporcionado");
                return null;
            });
        } catch (Exception ex) {
            log.error("Error al consumir el servicio de actualizar el rol: {}", ex.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteRolById(Long id) {
        if(id == null) {
            log.info("El id no puede venir vacio");
            return false;
        }
        Optional<CatalogoRoles> idOp = catalogoRolesRepository.findById(id);
        if(idOp.isPresent()) {
            log.info("El id si existe eliminando....");
            catalogoRolesRepository.deleteById(id);
            return true;
        }
        log.info("El id no existe");
        return false;
    }
}
