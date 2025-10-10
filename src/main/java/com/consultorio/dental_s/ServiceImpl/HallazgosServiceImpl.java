package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Hallazgos;
import com.consultorio.dental_s.Repository.HallazgosRepository;
import com.consultorio.dental_s.Services.HallazgosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HallazgosServiceImpl implements HallazgosService {

    @Autowired
    private HallazgosRepository hallazgosRepository;

    @Override
    public Hallazgos saveHallazgo(Hallazgos hallazgo) {
        try {
            log.info("Iniciando el servicio de saveHallazgo");
            Optional<Hallazgos> optHallazgo = hallazgosRepository.findByClave(hallazgo.getClave());
            if(optHallazgo.isPresent()){
                log.info("Hallazgo existente");
                return null;
            }
            log.info("Iniciando el servicio de saveHallazgo");
            return hallazgosRepository.save(hallazgo);
        } catch (Exception e) {
            log.error("Error al consumir el servicio de hallazgos {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Hallazgos> findAllHallazgos() {
        try {
            log.info("Iniciando el servicio de findAllHallazgos");
            return hallazgosRepository.findAll();
        } catch (Exception e) {
            log.error("Error al consumir el servicio de buscar todos los hallazgos {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Hallazgos updateHallazgo(Long id, Hallazgos hallazgo) {
        try {
            if(id == null){
                log.info("El id no puede venir nulo");
                return null;
            }
            log.info("Iniciando el servicio de updateHallazgo");
            return hallazgosRepository.findById(id).map((hallazgoUpdate) -> {
                hallazgoUpdate.setClave(hallazgo.getClave());
                hallazgoUpdate.setDescripcion(hallazgo.getDescripcion());
                hallazgoUpdate.setObservaciones(hallazgo.getObservaciones());
                hallazgoUpdate.setRutaImagen(hallazgo.getRutaImagen());
                hallazgoUpdate.setCatalogoOpcionesHallazgos(hallazgo.getCatalogoOpcionesHallazgos());
                hallazgoUpdate.setConsultas(hallazgo.getConsultas());
                return hallazgosRepository.save(hallazgoUpdate);
            }).orElseThrow(() -> {
                log.info("Hallazgo no encontrado");
                return null;
            });
        } catch (Exception e) {
            log.error("Error al consumir el servicio de updateHallazgo {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteHallazgo(Long id) {
        try {
            if (id == null) {
                log.info("El id no puede venir vacio");
            }
            if(hallazgosRepository.existsById(id)){
                log.info("El hallazgo existe, procedo a eliminarlo");
                hallazgosRepository.deleteById(id);
                return true;
            } else {
                log.info("Hallazgo no encontrado o ya fue eliminado anteriormente");
                return false;
            }
        } catch (Exception e) {
            log.error("Error al consumir el servicio de deleteHallazgo {}", e.getMessage());
            return false;
        }
    }
}
