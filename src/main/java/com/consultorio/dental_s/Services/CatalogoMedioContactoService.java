package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoMedioContacto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoMedioContactoService {

    CatalogoMedioContacto saveMedioContacto(CatalogoMedioContacto catalogoMedioContacto);

    List<CatalogoMedioContacto> findAllMedioContacto();

    CatalogoMedioContacto updatemMedioContacto(Long idMedioContacto, CatalogoMedioContacto catalogoMedioContacto);

    void deleteMedioContacto(Long idMedioContacto);
}
