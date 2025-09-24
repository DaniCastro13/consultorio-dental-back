package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoConceptos;
import com.consultorio.dental_s.Repository.CatalogoConceptosRepository;
import com.consultorio.dental_s.Services.CatalogoConceptosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoConceptosServiceImpl implements CatalogoConceptosService {

    @Autowired
    private CatalogoConceptosRepository catalogoConceptosRepository;

    @Transactional
    @Override
    public CatalogoConceptos saveConcepto(CatalogoConceptos catalogoConceptos) {
        try {
            Optional<CatalogoConceptos> cveGasto = catalogoConceptosRepository.findByClaveGasto(catalogoConceptos.getClaveGasto());
            if(cveGasto.isPresent()){
                log.info("El gasto ya existe en el sistema");
                return null;
            }
            log.info("El gasto no existe en el sistema, inicia proceso de guardao");
            return catalogoConceptosRepository.save(catalogoConceptos);
        } catch (Exception e) {
            log.error("Error al consumir el servicio de guardar el concepto {}", e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CatalogoConceptos> getCatalogoConceptos() {
        try {
            log.info("Inicia proceso de consultar los catalogoConceptos");
            List<CatalogoConceptos> listGastos = catalogoConceptosRepository.findByGastosActivos();
            if(!listGastos.isEmpty()){
                log.info("Si obtuve la lista");
                return listGastos;
            }
            log.info("La lista esta vacia");
            return List.of();
        } catch (Exception ex) {
            log.info("Error al consumir el servicio de consultar el conceptos {}", ex.getMessage());
            return List.of();
        }
    }

    @Transactional
    @Override
    public CatalogoConceptos updateConceptoById(Long id, CatalogoConceptos catalogoConceptos) {
        try {
            log.info("Inicia proceso de actualizar el concepto {}", id);
            log.info("Actualizando el concepto {}", catalogoConceptos);
            return catalogoConceptosRepository.findById(id).map((gasto) -> {
                gasto.setClaveGasto(catalogoConceptos.getClaveGasto());
                gasto.setNombreGasto(catalogoConceptos.getNombreGasto());
                gasto.setEstatusGasto(catalogoConceptos.getEstatusGasto());
                return catalogoConceptosRepository.save(gasto);
            }).orElseThrow(() -> {
                log.info("El concepto {} no existe en el sistema", id);
                return new Exception("El concepto con el id " + id + " no existe en el sistema");
            });
        } catch (Exception e) {
            log.error("Error al actualizar el concepto {}", e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteConceptoById(Long id) {
        try {
            log.info("Inicia proceso de eliminar el concepto {}", id);
            Optional<CatalogoConceptos> gasto = catalogoConceptosRepository.findById(id);
            if(gasto.isPresent()){
                log.info("Eliminando el concepto {}", id);
                catalogoConceptosRepository.deleteById(id);
                return true;
            }
            log.info("El concepto {} no existe en el sistema", id);
            return false;
        } catch (Exception e) {
            log.error("Error al eliminar el concepto {}", e.getMessage());
            return false;
        }
    }
}
