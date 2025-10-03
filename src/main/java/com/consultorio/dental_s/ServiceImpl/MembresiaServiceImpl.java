package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Membresias;
import com.consultorio.dental_s.Repository.MembresiaRepository;
import com.consultorio.dental_s.Services.MembresiaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Override
    public Membresias saveMembresia(Membresias membresias) {
        try {
            log.info("Iniciando servicio para almacenar");
            Optional<Membresias> getClave = membresiaRepository.findByClave(membresias.getClave());
            if (getClave.isPresent()) {
                log.info("La membresi ya existe");
                return null;
            }
            log.info("La membresia no existe, procede a eliminarse");
            return membresiaRepository.save(membresias);
        } catch (Exception e) {
            log.error("Error al consumir el servicio para guardar la membresia {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Membresias> findAllMembresias() {
        try {
            List<Membresias> listMembresias = membresiaRepository.findAllMembresiasActivas();
            log.info("Tamaño de la lista {}", listMembresias.size());
            if(!listMembresias.isEmpty()) {
                log.info("Lista de membresias activas {}", listMembresias);
                return listMembresias;
            }
            return List.of();
        } catch (Exception e) {
            log.error("Error al consumir el servicio para obtener la lista de las membresias {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public Membresias udateMembresiaById(Long idMembresia, Membresias membresias) {
        try {
            log.info("Iniciando el consumo del servicio para actualizar membresia");
            if(idMembresia == null) {
                log.info("Se debe seleccionar un ID membresia");
                return null;
            }
            return membresiaRepository.findById(idMembresia).map((member) -> {
                member.setClave(membresias.getClave());
                member.setNombre(membresias.getNombre());
                member.setDescripcion(membresias.getDescripcion());
                member.setDentista(membresias.getDentista());
                member.setPromocion(membresias.getPromocion());
                member.setActivo(membresias.getActivo());
                return membresiaRepository.save(member);
            }).orElseThrow(() -> {
                log.info("No existe el ID membresia {}", idMembresia);
                return null;
            });
        } catch (Exception e) {
            log.error("Error al consumir el servicio para actualizar membresia {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteMembresiaById(Long idMembresia) {
        try {
            if (idMembresia == null || !membresiaRepository.existsById(idMembresia)) {
               log.info("La membresia no existe");
               return false;
            }
            membresiaRepository.deleteById(idMembresia);
            return true;
        } catch (Exception e) {
            log.error("Error al consumir el servicio para eliminar membresia {}", e.getMessage());
            return false;
        }
    }
}
