package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import com.consultorio.dental_s.Exceptions.CatalagoTipoSangreException;
import com.consultorio.dental_s.Exceptions.CatalogoEstadoCivilException;
import com.consultorio.dental_s.Repository.CatalogoEstadoCivilRepository;
import com.consultorio.dental_s.Services.CatalogoEstadoCivilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoEstadoCivilServiceImpl implements CatalogoEstadoCivilService {

    @Autowired
    private CatalogoEstadoCivilRepository catalogoEstadoCivilRepository;


    @Override
    public CatalogoEstadoCivil saveCatalogoEstadoCivil(CatalogoEstadoCivil catalogoEstadoCivil) {
        try {
            log.info("---- SAVE CATALOGO ESTADO CIVIL SERVICE ----");
            log.info("Iniciando validacion de duplicados");
            String claveEstadoCivil = catalogoEstadoCivilRepository.findByClave(catalogoEstadoCivil.getClaveEstadoCivil());
            if(catalogoEstadoCivil.getClaveEstadoCivil().equals(claveEstadoCivil)) {
                log.info("La clave {} ", catalogoEstadoCivil.getClaveEstadoCivil(), " ya se encuentra en base de datos");
                return null;
            }
            log.info("CATALOGO ESTADO CIVIL ALMACENADO CON EXITO");
            return catalogoEstadoCivilRepository.save(catalogoEstadoCivil);
        } catch (CatalagoTipoSangreException exception) {
            log.info("ERROR AL CONSUMIR EL SERVICIO DE GUARDAR EL CATALOGO TIPO SANGRE");
            return null;
        }
    }

    @Override
    public List<CatalogoEstadoCivil> getCatalogoEstadoCivil() {
        try {
            log.info("--- CONSUMIENDO EL SERVICIO DE GET CATALOGO ESTADO CIVIL ---");
            List<CatalogoEstadoCivil> listCatalogoEstadoCivil = catalogoEstadoCivilRepository.findAllCatalogoEstadoCivilByActive();
            if(!listCatalogoEstadoCivil.isEmpty()) {
                log.info("--- TRAJE RESULTADOS DE LA LISTA ---");
                log.info("LISTA OBTENIDA: {}", listCatalogoEstadoCivil);
                return listCatalogoEstadoCivil;
            }
            log.info("--- NO TRAJE RESULTADOS ---");
            return List.of();
        }catch (CatalagoTipoSangreException exception){
            log.error("--- ERROR AL CONSUMIR EL SERVICIO DE OBTENER LA LISTA ---");
            log.error("--- DESCRIPCION DEL ERROR {} ",exception.getMessage());
            return List.of();
        }
    }

    @Override
    public CatalogoEstadoCivil updateCatalogoEstadoCivilById(Long id, CatalogoEstadoCivil catalogoEstadoCivil) {
        try {
            log.info("--- INICIANDO LA ACTUALIZACION DEL CATALOGO ---");
            return catalogoEstadoCivilRepository.findById(id).map(response -> {
                response.setClaveEstadoCivil(catalogoEstadoCivil.getClaveEstadoCivil());
                response.setNombreEstadoCivil(catalogoEstadoCivil.getNombreEstadoCivil());
                response.setActivo(catalogoEstadoCivil.getActivo());
                return catalogoEstadoCivilRepository.save(response);
            })
            .orElseThrow(() -> {
                        log.warn("No se encontro el catalogo estado civil");
                        return null;
            }
            );
        } catch (CatalagoTipoSangreException exception) {
            log.error("ERROR AL ACTUALIZAR EL CATALOGO ESTADO CIVIL");
            return null;
        }
    }

    @Override
    public void deleteCatalogoEstadoCivilById(Long idEstadoCivil) {
        try {
            log.info("--- INICIANDO CON LA ELIMINACION DEL CATALOGO CON EL ID: {}", idEstadoCivil);
            Optional<CatalogoEstadoCivil> id = catalogoEstadoCivilRepository.findById(idEstadoCivil);
            if(id.isPresent()) {
                log.info("--- ELIMINANDO EL CATALOGO ---: {}", id.get().getNombreEstadoCivil());
                this.catalogoEstadoCivilRepository.deleteById(idEstadoCivil);
            }
            log.info("--- NO EXISTE EL REGISTRO ---");
        } catch (CatalogoEstadoCivilException exception) {
            log.error("--- ERROR AL CONSUMIR EL SERVICIO DE ELIMINAR EL CATALOGO ---");
            log.error("--- DESCRIPCION DEL ERROR {} ",exception.getMessage());
        }
    }
}
