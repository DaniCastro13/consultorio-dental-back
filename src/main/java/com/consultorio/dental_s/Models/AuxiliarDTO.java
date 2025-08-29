package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.Pacientes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuxiliarDTO {

    private Long idAuxiliar;
    private Pacientes paciente;
    private String parentesco;
    private List<PacientesDTO> pacientes;
}
