package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CatalogoEstadoCivilService {

    CatalogoEstadoCivil saveCatalogoEstadoCivil(CatalogoEstadoCivil catalogoEstadoCivil);

    List<CatalogoEstadoCivil> getCatalogoEstadoCivil();

    CatalogoEstadoCivil updateCatalogoEstadoCivilById(Long id, CatalogoEstadoCivil catalogoEstadoCivil);

    void deleteCatalogoEstadoCivilById(Long idEstadoCivil);
}
