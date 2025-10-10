package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Citas;
import com.consultorio.dental_s.Repository.CitasRepository;
import com.consultorio.dental_s.Services.CitasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Override
    public Citas saveCita(Citas citas) {
        try {
            log.info("Se inicio el servicio para almacenar citas");
            return citasRepository.save(citas);
        } catch (Exception e) {
            log.error("Error al insertar el cita {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Citas> getAllCitas() {
        try {
            log.info("Se inicio el servicio getAllCitas");
            return citasRepository.findAll();
        }  catch (Exception e) {
            log.error("Error al consultar los citas {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Citas updateCita(Long idCita, Citas citas) {
        try {
            if(idCita == null) {
                log.info("El id no puede venir nulo");
                return null;
            }
            return citasRepository.findById(idCita).map((citaUpdate) -> {
                citaUpdate.setFechaCita(citas.getFechaCita());
                citaUpdate.setHoraCita( citas.getHoraCita());
                citaUpdate.setObservaciones( citas.getObservaciones());
                citaUpdate.setAsistencia(citas.getAsistencia());
                citaUpdate.setDentista( citas.getDentista());
                citaUpdate.setPaciente( citas.getPaciente());
                return citasRepository.save(citaUpdate);
            }).orElseThrow(() -> {
                log.info("El id no puede tener el cita no existe");
                return new Exception("El id no puede tener el cita no existe");
            });
        } catch (Exception e) {
            log.error("Error al actualizar el cita {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteCita(Long idCita) {
        try {
            log.info("Iniciando el servicio para eliminar la cita {}", idCita);
            if(idCita == null || !citasRepository.existsById(idCita)) {
                log.info("El id no puede venir nulo");
                return false;
            }
            citasRepository.deleteById(idCita);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar el cita {}", e.getMessage());
            return  false;
        }
    }
}
