package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoEstadoCivilDTO {

    private Long idCatalogoEstadoCivil;
    private String claveEstadoCivil;
    private String nombreEstadoCivil;
    private Boolean activo;
}
