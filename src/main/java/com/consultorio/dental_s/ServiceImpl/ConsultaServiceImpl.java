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
    private ConsultasRepsotory consultaRepsotory;

    @Override
    public Consultas saveConsulta(Consultas consulta) {
        try {
            log.info("Iniciando el servicio para almacenar la consulta");
            return consultaRepsotory.save(consulta);
        } catch (Exception e) {
            log.error("Error al almacenar la consulta {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Consultas> getAllConsultas() {
        try {
            log.info("Iniciando la pagina de consultas");
            return consultaRepsotory.findAll();
        } catch (Exception e) {
            log.error("Error al obtener las consultas {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Consultas updateConsulta(Long idConsulta, Consultas consulta) {
        if(idConsulta == null) {
            log.info("El id no puede ser nulo");
            return null;
        }
        return consultaRepsotory.findById(idConsulta).map((consultaUpdate) -> {
            consultaUpdate.setAlergico(consulta.getAlergico());
            consultaUpdate.setPeso(consulta.getPeso());
            consultaUpdate.setEstatura(consulta.getEstatura());
            consultaUpdate.setPadecimiento(consulta.getPadecimiento());
            consultaUpdate.setObservaciones(consulta.getObservaciones());
            return consultaRepsotory.save(consultaUpdate);
        }).orElseThrow(() -> {
            log.info("La consulta no existe");
            return null;
        });
    }

    @Override
    public boolean deleteConsulta(Long idConsulta) {
        try {
            log.info("Iniciando el eliminado de la consulta");
            if(consultaRepsotory.existsById(idConsulta)) {
                log.info("La consulta existe en el sistema");
                consultaRepsotory.deleteById(idConsulta);
                return true;
            }
            log.info("La consulta no existe en el sistema");
            return false;
        } catch (Exception e) {
            log.error("Error al eliminar la consulta {}", e.getMessage());
            return false;
        }
    }
}
