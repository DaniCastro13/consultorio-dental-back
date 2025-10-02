package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Pacientes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacientesService {

    Pacientes savePaciente(Pacientes paciente);

    List<Pacientes> findAllPacientes();

    Pacientes updatePacienteById(Long id, Pacientes paciente);

    boolean deletePacienteById(Long id);
}
