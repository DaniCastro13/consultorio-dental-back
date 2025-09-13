package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoMedioContacto;
import com.consultorio.dental_s.Entities.MedioContacto;
import com.consultorio.dental_s.Exceptions.MedioContactoException;
import com.consultorio.dental_s.Repository.CatalogoMedioContactoRepository;
import com.consultorio.dental_s.Repository.MedioContactoRepository;
import com.consultorio.dental_s.Services.MedioContactoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MedioContactoServiceImpl implements MedioContactoService {

    @Autowired
    private MedioContactoRepository medioContactoRepository;

    @Autowired
    private CatalogoMedioContactoRepository catalogoMedioContactoRepository;

    @Override
    public MedioContacto saveContacto(MedioContacto medioContacto) throws MedioContactoException {

            log.info("Iniciando el servicio --saveContacto--");
            log.info("Request medio contacto: {}", medioContacto);
            Optional<CatalogoMedioContacto> getId = catalogoMedioContactoRepository.findById(medioContacto.getCatalogoMedioContacto().getIdCatalogoMedioContacto());
            if(!getId.isPresent()){
                log.info("No esta registrado en el catalogo de medio contacto");
                throw new MedioContactoException("No existe el catalogo de medio contacto");
            }
            String claveMedio = medioContactoRepository.findyByClave(medioContacto.getValor());
            if(medioContacto.getValor().equals(claveMedio)) {
                log.info("El medio contacto ya existe");
                return null;
            }
            return this.medioContactoRepository.save(medioContacto);
    }

    @Override
    public List<MedioContacto> findAllContacto() throws MedioContactoException {
        List<MedioContacto> listMedioContacto = medioContactoRepository.findAlldMedioContacto();
        if(!listMedioContacto.isEmpty()) {
            return listMedioContacto;
        }
        throw new MedioContactoException("La lista del medio contacto esta vacia");
    }

    @Override
    public MedioContacto updateContacto(Long id, MedioContacto medioContacto) throws MedioContactoException {
        log.info("Iniciando el servicio --updateContacto--");
        log.info("Request medio contacto: {}", medioContacto);
        if(id==null){
            log.info("El id no puede venir vacio");
            throw new MedioContactoException("El id no puede venir vacio");
        }
        return medioContactoRepository.findById(id).map((response) -> {
            response.setValor(medioContacto.getValor());
            response.setActivo(medioContacto.getActivo());
            response.setCatalogoMedioContacto(medioContacto.getCatalogoMedioContacto());
            return medioContactoRepository.save(response);
        }).orElseThrow(() -> {
            log.info("No existe el medio de contacto con el id: {}", id);
            throw new MedioContactoException("No existe el medio de contacto con el id: " + id);
        });
    }

    @Override
    public void deleteContactoById(Long id) throws MedioContactoException {
        log.info("Iniciando el servicio --deleteContacto--");
        if(id==null){
            log.info("El id no puede venir vacio");
            throw new MedioContactoException("El id no puede venir vacio");
        }
        Optional<MedioContacto> findById = medioContactoRepository.findById(id);
        if(findById.isPresent()){
            this.medioContactoRepository.deleteById(id);
        } else {
            log.info("No existe el medio de contacto con el id: {}", id);
            throw new MedioContactoException("No existe el medio de contacto con el id: " + id);
        }

    }
}
