package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoConceptosDTO {

    private Long idCatGasto;
    private String claveGasto;
    private String nombreGasto;
    private Boolean estatusGasto;
}
