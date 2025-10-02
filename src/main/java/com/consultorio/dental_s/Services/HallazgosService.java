package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Hallazgos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HallazgosService {

    Hallazgos saveHallazgo(Hallazgos hallazgo);

    List<Hallazgos> findAllHallazgos();

    Hallazgos updateHallazgo(Long id, Hallazgos hallazgo);

    boolean deleteHallazgo(Long id);
}
