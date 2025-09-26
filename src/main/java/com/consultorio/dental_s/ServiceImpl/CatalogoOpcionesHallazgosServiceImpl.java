package com.consultorio.dental_s.ServiceImpl;


import com.consultorio.dental_s.Entities.CatalogoOpcionesHallazgos;
import com.consultorio.dental_s.Repository.CatalogoOpcionesHallazgosRepository;
import com.consultorio.dental_s.Services.CatalogoOpcionesHallazgoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoOpcionesHallazgosServiceImpl implements CatalogoOpcionesHallazgoService {

    @Autowired
    private CatalogoOpcionesHallazgosRepository catalogoOpcionesHallazgosRepository;

    @Override
    public CatalogoOpcionesHallazgos saveOpcionHallazgo(CatalogoOpcionesHallazgos catalogoOpcionesHallazgos) {
        try {
            log.info("Iniciando el servicio saveOpcionHallazgo");
            Optional<CatalogoOpcionesHallazgos> getClave = catalogoOpcionesHallazgosRepository.findByClave(catalogoOpcionesHallazgos.getClaveHallazgo());
            if(!getClave.isPresent()){
                log.info("El servicio saveOpcionHallazgo no existe");
                log.info("Iniciando el guardado de la opcion hallazgo");
                return catalogoOpcionesHallazgosRepository.save(catalogoOpcionesHallazgos);
            }
            log.info("El servicio saveOpcionHallazgo existe");
            return null;
        } catch (Exception e) {
            log.error("Error al guardar el servicio saveOpcionHallazgo", e.getMessage());
            return null;
        }
    }

    @Override
    public List<CatalogoOpcionesHallazgos> findAllOpcionesHallazgos() {
        try {
            log.info("Iniciando el servicio para extraer la lista de la opcion hallazgo");
            List<CatalogoOpcionesHallazgos> listOptionHallazgo = catalogoOpcionesHallazgosRepository.findAll();
            if(listOptionHallazgo.isEmpty()){
                log.info("La lista de la opcion hallazgo esta vacia");
                return List.of();
            }
            return listOptionHallazgo;
        } catch (Exception e) {
            log.error("Error al obtener las opcionesHallazgos {}", e.getMessage());
            return null;
        }
    }

    @Override
    public CatalogoOpcionesHallazgos updateOpcionHallazgo(Long id, CatalogoOpcionesHallazgos catalogoOpcionesHallazgos) {
        try {
            if(id == null){
                log.info("El id no puede venir null");
                return null;
            }
            return catalogoOpcionesHallazgosRepository.findById(id).map((hallazgo) -> {
                hallazgo.setClaveHallazgo(catalogoOpcionesHallazgos.getClaveHallazgo());
                hallazgo.setNombreHallazgo(catalogoOpcionesHallazgos.getNombreHallazgo());
                return catalogoOpcionesHallazgosRepository.save(hallazgo);
            }).orElseThrow(() -> {
                log.info("No existe el hallazgo que desea actualizar");
                return null;
            });
        } catch (Exception e) {
            log.error("Error al actualizar la opcion hallazgo {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteOpcionHallazgo(Long id) {
        try {
            Optional<CatalogoOpcionesHallazgos> getHallazgo = catalogoOpcionesHallazgosRepository.findById(id);
            if (getHallazgo.isPresent()) {
                catalogoOpcionesHallazgosRepository.deleteById(id);
                return true;
            }
            log.info("El hallazgo que desea eliminar ya no existe en el sistema");
            return false;
        } catch (Exception e) {
            log.error("Error al eliminar el hallazgo {}", e.getMessage());
            return false;
        }
    }
}
