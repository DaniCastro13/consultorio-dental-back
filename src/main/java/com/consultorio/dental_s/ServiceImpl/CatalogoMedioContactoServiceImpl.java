package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoMedioContacto;
import com.consultorio.dental_s.Exceptions.CatalogoMedioContactoException;
import com.consultorio.dental_s.Repository.CatalogoMedioContactoRepository;
import com.consultorio.dental_s.Services.CatalogoMedioContactoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatalogoMedioContactoServiceImpl implements CatalogoMedioContactoService {

    @Autowired
    private CatalogoMedioContactoRepository catalogoMedioContactoRepository;

    @Override
    public CatalogoMedioContacto saveMedioContacto(CatalogoMedioContacto catalogoMedioContacto) throws CatalogoMedioContactoException {
        log.info("Iniciado el metodo saveMedioContacto");
        log.info("Request medio contacto: {}", catalogoMedioContacto);
        String claveExist = catalogoMedioContactoRepository.findByClave(catalogoMedioContacto.getClave());
        if(catalogoMedioContacto.getClave().equals(claveExist)){
            throw new CatalogoMedioContactoException("Clave ya existente");
        }
        return catalogoMedioContactoRepository.save(catalogoMedioContacto);
    }

    @Override
    public List<CatalogoMedioContacto> findAllMedioContacto() throws CatalogoMedioContactoException {
        List<CatalogoMedioContacto> listMediosContactos = catalogoMedioContactoRepository.findMedioContactoByActivo();
        log.info("Iniciado el metodo findAllMedioContacto");
        if(listMediosContactos.isEmpty()){
            log.info("No encontre resultados");
            throw new CatalogoMedioContactoException("No se encontraron medios contactos activos");
        }
        log.info("Encontre medios contactos activos");
        log.info("Lista obtentida: {}", listMediosContactos);
        return listMediosContactos;
    }

    @Override
    public CatalogoMedioContacto updatemMedioContacto(Long idMedioContacto, CatalogoMedioContacto catalogoMedioContacto) throws CatalogoMedioContactoException{
        log.info("Iniciando la actualizacion del medio de contacto con el id {}", idMedioContacto);
        log.info("Request recibido para actualizar: {}", catalogoMedioContacto);
        return catalogoMedioContactoRepository.findById(idMedioContacto).map(response -> {
            response.setClave(catalogoMedioContacto.getClave());
            response.setNombre(catalogoMedioContacto.getNombre());
            response.setActivo(catalogoMedioContacto.getActivo());
            return catalogoMedioContactoRepository.save(response);
        }).orElseThrow(() -> {
            log.info("No existe el medio de contacto con el id {}", idMedioContacto);
            throw new CatalogoMedioContactoException("No existe este medio de contacto");
        });
    }

    @Override
    public void deleteMedioContacto(Long idMedioContacto) throws CatalogoMedioContactoException {
        log.info("Iniciando el eliminado del medio contacto con el id: {}", idMedioContacto);
        if(idMedioContacto == null){
            throw new CatalogoMedioContactoException("El medio de contacto no puede ser null");
        }
        Optional<CatalogoMedioContacto> findById = catalogoMedioContactoRepository.findById(idMedioContacto);
        if(findById.isPresent()){
            this.catalogoMedioContactoRepository.deleteById(idMedioContacto);
        }
        throw new CatalogoMedioContactoException("El medio de contacto no existe");
    }
}
