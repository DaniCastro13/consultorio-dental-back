package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Gastos;
import com.consultorio.dental_s.Repository.GastosRepository;
import com.consultorio.dental_s.Services.GastosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GastosServiceImpl implements GastosService {

    @Autowired
    private GastosRepository gastosRepository;

    @Override
    public Gastos saveGasto(Gastos gasto) {
        try {
            log.info("Iniciando el servicio para guardar un gasto");
            return gastosRepository.save(gasto);
        } catch (Exception e) {
            log.error("Error al guardar el gasto: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Gastos> getAllGastos() {
        try {
            log.info("Iniciando el servicio para obtener la lista de gastos");
            return gastosRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener la lista de gastos: {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Gastos updateGastoById(Long idGasto, Gastos gasto) {
        try {
            if(idGasto == null || idGasto <= 0) {
                log.warn("El ID del gasto es nulo o inválido");
                return null;
            }
            log.info("Iniciando el servicio para actualizar el gasto con ID: {}", idGasto);
            return gastosRepository.findById(idGasto).map(existingGasto -> {
              existingGasto.setImporte(gasto.getImporte());
              existingGasto.setJustificacionGasto(gasto.getJustificacionGasto());
              existingGasto.setObservaciones(gasto.getObservaciones());
              existingGasto.setCatalogoConceptos(gasto.getCatalogoConceptos());
              return gastosRepository.save(existingGasto);
            }).orElseThrow(() -> {
                log.info("No existe el gasto con el id: {}", idGasto);
                return null;
            });
        } catch (Exception e ) {
            log.error("Error al actualizar el gasto: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteGastoById(Long idGasto) {
        try {
            if(idGasto == null || idGasto <= 0) {
                log.warn("El ID del gasto es nulo o inválido");
                return false;
            }
            if(gastosRepository.existsById(idGasto)) {
                log.info("Iniciando el servicio para eliminar el gasto con ID: {}", idGasto);
                gastosRepository.deleteById(idGasto);
                return true;
            } else {
                log.info("No existe el gasto con el id: {}", idGasto);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al eliminar el gasto: {}", e.getMessage());
            return false;
        }
    }
}
