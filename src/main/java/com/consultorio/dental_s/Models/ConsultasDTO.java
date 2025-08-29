package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.Citas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultasDTO {

    private Long idConsulta;
    private String alergico;
    private Double peso;
    private Double estatura;
    private String padecimiento;
    private String observaciones;
    private Citas cita;
    private List<CatalogoProcedimientosDTO> catalogoProcedimientos;
    private List<HallazgosDTO> hallazgos;
}
