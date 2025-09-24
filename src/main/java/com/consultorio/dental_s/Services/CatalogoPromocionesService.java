package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoPromociones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoPromocionesService {

    CatalogoPromociones savePromocion(CatalogoPromociones promocion);

    List<CatalogoPromociones> listaPromociones();

    CatalogoPromociones updatePromocionById(Long id, CatalogoPromociones promocion);

    boolean deletePromocionById(Long id);

}
