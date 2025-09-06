package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import com.consultorio.dental_s.Entities.CatalogoSexo;
import com.consultorio.dental_s.Exceptions.CatalogoSexoException;
import com.consultorio.dental_s.Repository.CatalogoSexoRepository;
import com.consultorio.dental_s.Services.CatalogoSexoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoSexoServiceImpl implements CatalogoSexoService {

    @Autowired
    private CatalogoSexoRepository catalogoSexoRepository;

    @Override
    public CatalogoSexo saveCatalogoSexo(CatalogoSexo catalogoSexo) {
        try {
            log.info("--- INICIO DEL SERVICIO SAVE CATALOGO SEXO ---");
            log.info("REQUEST CATALOGO SEXO: {}", catalogoSexo);
            String claveCat = catalogoSexoRepository.findByClave(catalogoSexo.getClave());

            if(claveCat == null) {
                log.info("--- NO EXISTE ESTA CLAVE ---");
                return catalogoSexoRepository.save(catalogoSexo);
            }
                log.info("--- YA EXISTE UN CATALOGO CON ESA CLAVE ---");
                return null;
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("--- ERROR AL CONSUMIR EL SERVICIO DE GUARDAR CATALOGO SEXO ---");
            log.info("DESCRIPTION MESSAGE: {}", catalogoSexoException.getMessage());
            return null;
        }
    }

    @Override
    public List<CatalogoSexo> getAllCatalogoSexo() {
        try {
            log.info("--- INICIANDO EL SERVICIO DE GETALLCATALOGOSEXO ---");
            List<CatalogoSexo> listCatalogoSexo = catalogoSexoRepository.findAllCatalogoSexoByActive();
            if(listCatalogoSexo.isEmpty()) {
                log.info("--- NO EXISTE CATALOGOS ACTIVOS ---");
                return List.of();
            }
            return listCatalogoSexo;
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("--- ERROR AL CONSUMIR EL CONTROLADOR SAVE CATALOGO SEXO ---");
            log.info("DESCRIPTION MESSAGE: {}", catalogoSexoException.getMessage());
            return List.of();
        }
    }

    @Override
    public CatalogoSexo updateCatalogoSexoById(Long idCatalogoSexo, CatalogoSexo catalogoSexo) {
        try {
            log.info("--- INICIANDO LA ACTUALIZACION DEL CATALOGO DE SEXO {}", catalogoSexo);
        return catalogoSexoRepository.findById(idCatalogoSexo).map(response -> {
           response.setClave(catalogoSexo.getClave());
           response.setNombre(catalogoSexo.getNombre());
           response.setActivo(catalogoSexo.getActivo());
           return catalogoSexoRepository.save(response);
        })
        .orElseThrow(() -> {
            log.warn("--- NO EXISTE CATALOGO SEXO ---");
            return null;
        });
        } catch (CatalogoSexoException catalogoSexoException) {
            log.info("--- ERROR AL CONSUMIR EL SERVICIO DE ACTUALIZAR EL CATALOGO ---");
            return null;
        }
    }

    @Override
    public void deleteCatalogoById(Long id) {
        try {
            Optional<CatalogoSexo> catSexo = catalogoSexoRepository.findById(id);
            if (catSexo.isPresent()) {
                log.info("--- ELIMINANDO EL CATALOGO DE SEXO CON EL ID: {} ---", id);
                catalogoSexoRepository.deleteById(id);
            }
            log.info("--- NO EXISTE EL REGISTRO ---");
        } catch (CatalogoSexoException catalogoSexoException) {
            log.error("--- NO SE CONSUMIO EL SERVICIO PARA ELIMINAR ---");
            log.info("DESCRIPTION MESSAGE: {}", catalogoSexoException.getMessage());
        }
    }
}
