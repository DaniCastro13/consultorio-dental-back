package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Consultas;
import com.consultorio.dental_s.Repository.ConsultasRepsotory;
import com.consultorio.dental_s.Services.ConsultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultasRepsotory consultasRepsotory;

    @Override
    public Consultas saveConsulta(Consultas consultas) {
        try {
            log.info("Iniciando el servicio de guardado de la consulta");
            return consultasRepsotory.save(consultas);
        } catch (Exception e) {
            log.error("Error al consumir el servicio de guardar la consulta {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Consultas> findAllConsultas() {
        try {
            log.info("Iniciando el servicio de consultas");
            return consultasRepsotory.findAll();
        } catch (Exception e) {
            log.error("Error al consumir el servicio de consultar las consultas {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Consultas updateConsulta(Long idConsulta, Consultas consultas) {
        try {
            if(idConsulta==null){
                log.info("El id de la consulta es nulo");
                return null;
            }
            return consultasRepsotory.findById(idConsulta).map((consultaUpdate) -> {
                consultaUpdate.setAlergico(consultas.getAlergico());
                consultaUpdate.setPeso(consultas.getPeso());
                consultaUpdate.setEstatura(consultas.getEstatura());
                consultaUpdate.setPadecimiento(consultas.getPadecimiento());
                consultaUpdate.setObservaciones(consultas.getObservaciones());
                consultaUpdate.setCita(consultas.getCita());
                consultaUpdate.setCatalogoProcedimientos(consultas.getCatalogoProcedimientos());
                consultaUpdate.setHallazgos(consultas.getHallazgos());
                return consultasRepsotory.save(consultaUpdate);
            }).orElseGet(() -> {
                log.info("No se encontro la consulta con el id {}", idConsulta);
                return null;
            });
        } catch (Exception e) {
            log.error("Error al consumir el servicio de actualizar la consulta {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteConsulta(Long idConsulta) {
        try {
            log.info("Iniciando el servicio de eliminar la consulta con id {}", idConsulta);
            if(consultasRepsotory.existsById(idConsulta)){
                consultasRepsotory.deleteById(idConsulta);
                return true;
            } else {
                log.info("No se encontro la consulta con el id {}", idConsulta);
                return false;
            }
        } catch (Exception e) {
            log.error("Error al consumir el servicio de eliminar la consulta {}", e.getMessage());
            return false;
        }
    }
}
