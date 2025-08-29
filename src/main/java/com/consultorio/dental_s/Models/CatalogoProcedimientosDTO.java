package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoProcedimientosDTO {

    private Long idCatProcedimiento;
    private String nombreProcedimiento;
    private String descripcion;
    private String observaciones;
    private Double precioEstandar;
    private Double precioEspecial;
    private Integer duracionEstimada; // Duration in minutes
    private Boolean activo;
}
