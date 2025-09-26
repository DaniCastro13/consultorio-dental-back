package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoOpcionesHallazgos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoOpcionesHallazgoService {

    CatalogoOpcionesHallazgos saveOpcionHallazgo(CatalogoOpcionesHallazgos catalogoOpcionesHallazgos);

    List<CatalogoOpcionesHallazgos> findAllOpcionesHallazgos();

    CatalogoOpcionesHallazgos updateOpcionHallazgo(Long id, CatalogoOpcionesHallazgos catalogoOpcionesHallazgos);

    boolean deleteOpcionHallazgo(Long id);
}
