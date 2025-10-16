package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Consultas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultaService {

    Consultas saveConsulta(Consultas consultas);

    List<Consultas> findAllConsultas();

    Consultas updateConsulta(Long idConsulta, Consultas consultas);

    boolean deleteConsulta(Long idConsulta);
}
