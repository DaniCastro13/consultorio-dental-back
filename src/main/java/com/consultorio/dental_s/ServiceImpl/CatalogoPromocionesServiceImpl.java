package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoPromociones;
import com.consultorio.dental_s.Repository.CatalogoPromocionesRepository;
import com.consultorio.dental_s.Services.CatalogoPromocionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoPromocionesServiceImpl implements CatalogoPromocionesService {

    @Autowired
    private CatalogoPromocionesRepository catalogoPromocionesRepository;

    @Transactional
    @Override
    public CatalogoPromociones savePromocion(CatalogoPromociones promocion) {
        try {
            log.info("Iniciando el servicio de savePromocion");
            Optional<CatalogoPromociones> cveNombre = catalogoPromocionesRepository.findByNombre(promocion.getNombre());
            if(cveNombre.isPresent()){
                log.info("La promocion existe en el sistema");
                return null;
            }
            return catalogoPromocionesRepository.save(promocion);
        } catch (Exception e) {
            log.info("Error al insertar el servicio de savePromocion");
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CatalogoPromociones> listaPromociones() {
        try {
            List<CatalogoPromociones> lista = catalogoPromocionesRepository.findAll();
            if(lista.isEmpty()){
                log.info("No existen promociones en el sistema");
                return List.of();
            }
            return lista;
        } catch (Exception e) {
            log.info("Error al listar el servicio de listaPromociones");
            return List.of();
        }
    }

    @Transactional
    @Override
    public CatalogoPromociones updatePromocionById(Long id, CatalogoPromociones promocion) {
        if(id == null) {
            log.info("El id no puede venir vacio");
            return null;
        }
        log.info("Iniciando el servicio de updatePromocion");
        return catalogoPromocionesRepository.findById(id).map((updatePromocion) -> {
            updatePromocion.setNombre(promocion.getNombre());
            updatePromocion.setCodigoPromocion(promocion.getCodigoPromocion());
            updatePromocion.setFechaInicio(promocion.getFechaInicio());
            updatePromocion.setFechaTermino(promocion.getFechaTermino());
            return catalogoPromocionesRepository.save(updatePromocion);
        }).orElseThrow(() -> {
            log.info("No existe registrada en el sistema la promocion a actualizar");
            return null;
        });
    }

    @Override
    public boolean deletePromocionById(Long id) {
        try {
            Optional<CatalogoPromociones> getId = catalogoPromocionesRepository.findById(id);
            if(id == null || !getId.isPresent()) {
                log.info("El id no puede venir vacio");
                return false;
            }
            catalogoPromocionesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar el servicio de deletePromocion");
            return false;
        }
    }
}
