package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoTipoSangre;

import java.util.List;
import java.util.Optional;

public interface CatalogoTipoSangreService {

    CatalogoTipoSangre saveCatalogoTipoSangre(CatalogoTipoSangre catalogoTipoSangre);

    List<CatalogoTipoSangre> findAllCatalogoTipoSangre();

    CatalogoTipoSangre updateCatalogoTipoSangre(Long idTipoSangre, CatalogoTipoSangre catalogoTipoSangre);

    void deleteCatalogoTipoSangre(Long idTipoSangre);
}
