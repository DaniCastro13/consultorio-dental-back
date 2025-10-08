package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.Pacientes;
import com.consultorio.dental_s.Repository.PacientesRepository;
import com.consultorio.dental_s.Services.PacientesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PacientesServiceImpl implements PacientesService {

    @Autowired
    private PacientesRepository pacientesRepository;

    @Override
    public Pacientes savePaciente(Pacientes paciente) {
        try {
            log.info("Iniciando el consumo del servicio --savePaciente--");
            log.info("REQUEST: {}", paciente);
            Optional<Pacientes> existCurp = pacientesRepository.findByCurp(paciente.getCurp());
            if (existCurp.isPresent()) {
                log.info("Paciente con la curp ya existente");
                return null;
            }
            log.info("El paciente no existe, procediendo a guardarlo");
            return pacientesRepository.save(paciente);
        } catch (Exception e) {
            log.info("Error al consumir el servicio --savePaciente-- {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Pacientes> findAllPacientes() {
        try {
            List<Pacientes> listAllPacientes = pacientesRepository.findAll();
            log.info("Iniciando el consumo del servicio --findAllPacientes");
            if(!listAllPacientes.isEmpty()) {
                log.info("RESPONSE: {}", listAllPacientes);
                return listAllPacientes;
            }
            log.info("No se encontraron pacientes");
            return List.of();
        } catch (Exception e) {
            log.info("Error al consumir el servicio --findAllPacientes");
            return List.of();
        }
    }

    @Override
    public Pacientes updatePacienteById(Long id, Pacientes paciente) {
        if(id == null) {
            log.info("El ID del paciente es nulo");
            return null;
        }
        return pacientesRepository.findById(id).map((pacient) -> {
            pacient.setNombre(paciente.getNombre());
            pacient.setApellidoPaterno(paciente.getApellidoPaterno());
            pacient.setApellidoMaterno(paciente.getApellidoMaterno());
            pacient.setDireccion(String.valueOf(paciente.getDireccion()));
            pacient.setTelefono(paciente.getTelefono());
            pacient.setCurp(paciente.getCurp());
            pacient.setClienteActivo(paciente.getClienteActivo());
            pacient.setMembresia(paciente.getMembresia());
            pacient.setCatalogoSexo(paciente.getCatalogoSexo());
            pacient.setCatalogoEstadoCivil(paciente.getCatalogoEstadoCivil());
            pacient.setCatalogoTipoSangre(paciente.getCatalogoTipoSangre());
            return pacientesRepository.save(pacient);
        }).orElseThrow(()-> {
            log.info("No se encontró el paciente con ID: {}", id);
            return null;
        });
    }

    @Override
    public boolean deletePacienteById(Long id) {
        try {
            if(id == null) {
                log.info("El ID del paciente es nulo");
                return false;
            }
            log.info("Iniciando el consumo del servicio --deletePacienteById--");
            Optional<Pacientes> existPaciente = pacientesRepository.findById(id);
            if(existPaciente.isPresent()) {
                pacientesRepository.deleteById(id);
                log.info("Paciente con ID: {} eliminado correctamente", id);
                return true;
            }
            log.info("No se encontró el paciente con ID: {}", id);
            return false;
        } catch (Exception e) {
            log.info("Error al consumir el servicio --deletePacienteById-- {}", e.getMessage());
            return false;
        }
    }
}
