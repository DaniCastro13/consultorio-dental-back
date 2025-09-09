package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import com.consultorio.dental_s.Exceptions.CatalagoTipoSangreException;
import com.consultorio.dental_s.Exceptions.CatalogoSexoException;
import com.consultorio.dental_s.Repository.CatalogoTipoSangreRepository;
import com.consultorio.dental_s.Services.CatalogoTipoSangreService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoTipoSangreServiceImpl implements CatalogoTipoSangreService {

    private final CatalogoTipoSangreRepository catalogoTipoSangreRepository;

    public CatalogoTipoSangreServiceImpl(CatalogoTipoSangreRepository catalogoTipoSangreRepository) {
        this.catalogoTipoSangreRepository = catalogoTipoSangreRepository;
    }

    @Transactional
    @Override
    public CatalogoTipoSangre saveCatalogoTipoSangre(CatalogoTipoSangre catalogoTipoSangre) {
        try {
            Optional<CatalogoTipoSangre> existingTipoSangre = catalogoTipoSangreRepository.findByName(catalogoTipoSangre.getCveTipoSangre(), catalogoTipoSangre.getNombreTipoSangre());
            if(existingTipoSangre.isPresent()) {
                throw new CatalagoTipoSangreException("Tipo de sangre ya existe");
            }
            return catalogoTipoSangreRepository.save(catalogoTipoSangre);
        } catch (Exception catalogoSexoException) {
            log.error("ERROR AL CONSUMIR EL SERVIVIO PARA ALMACENAR EL CATALOGO {}", catalogoSexoException.getMessage());
            return null;
        }
    }

    @Override
    public List<CatalogoTipoSangre> findAllCatalogoTipoSangre() {
        try {
            List<CatalogoTipoSangre> listCatalogoTipoSangre = catalogoTipoSangreRepository.findAllCatalogoTipoSangre();
            if(listCatalogoTipoSangre.isEmpty()) {
                log.info("No encontre informacion");
                throw new CatalagoTipoSangreException("Lista vacia");
            }
            log.info("Obtuve la siguiente lista {}", listCatalogoTipoSangre);
            return listCatalogoTipoSangre;
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("Error al consumir el servicio de buscar todos los catalogos");
            log.error(catalogoSexoException.getMessage());
            return List.of();
        }
    }

    @Override
    public CatalogoTipoSangre updateCatalogoTipoSangre(Long idTipoSangre, CatalogoTipoSangre catalogoTipoSangre) {
        try {
            log.info("INICIANDO LA ACTUALIZACION DEL CATALOGO DE SEXO");
            return catalogoTipoSangreRepository.findById(idTipoSangre).map(response -> {
                response.setCveTipoSangre(catalogoTipoSangre.getCveTipoSangre());
                response.setNombreTipoSangre(catalogoTipoSangre.getNombreTipoSangre());
                response.setActivo(catalogoTipoSangre.getActivo());
                return catalogoTipoSangreRepository.save(response);
            })
            .orElseThrow(() -> {
              log.info("No existe el catalogo solicitado");
              return null;
            });
        } catch (CatalagoTipoSangreException catalogoSexoException) {
            log.info("ERROR AL CONSUMIR EL SERVICIO DE ACTUALIZAR EL CATALOGO SOLICITADO");
            log.error(catalogoSexoException.getMessage());
            return null;
        }
    }


    @Override
    public void deleteCatalogoTipoSangre(Long idTipoSangre) {
        log.info("INICIANDO LA ELIMINACION DEL CATALOGO DE TIPO DE SANGRE");
        this.catalogoTipoSangreRepository.deleteById(idTipoSangre);
    }
}
