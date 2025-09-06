package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoSexo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoSexoService {

    CatalogoSexo saveCatalogoSexo(CatalogoSexo catalogoSexo);

    List<CatalogoSexo> getAllCatalogoSexo();

    CatalogoSexo updateCatalogoSexoById(Long idCatalogoSexo, CatalogoSexo catalogoSexo);

    void deleteCatalogoById(Long id);
}
