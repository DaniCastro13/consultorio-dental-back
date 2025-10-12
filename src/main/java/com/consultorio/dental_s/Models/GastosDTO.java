package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.CatalogoConceptos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastosDTO {

    private Long idGasto;
    private String fechaGasto;
    private Double importe;
    private String justificacionGasto;
    private String observaciones;
    private CatalogoConceptos catalogoConceptos;
}
