package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import com.consultorio.dental_s.Entities.Pacientes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitasDTO {

    private Long idCita;
    private LocalDateTime fechaCita;
    private Date horaCita;
    private String observaciones;
    private Boolean asistencia;
    private CatalogoDentistas dentista;
    private Pacientes paciente;
}
