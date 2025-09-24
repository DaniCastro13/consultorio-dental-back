package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoConceptos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoConceptosService {

    CatalogoConceptos saveConcepto(CatalogoConceptos catalogoConceptos);

    List<CatalogoConceptos> getCatalogoConceptos();

    CatalogoConceptos updateConceptoById(Long id, CatalogoConceptos catalogoConceptos);

    boolean deleteConceptoById(Long id);
}
