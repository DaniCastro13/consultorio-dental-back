package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Consultas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultaService {

    Consultas saveConsulta(Consultas consulta);

    List<Consultas> getAllConsultas();

    Consultas updateConsulta(Long idConsulta, Consultas consulta);

    boolean deleteConsulta(Long idConsulta);
}
