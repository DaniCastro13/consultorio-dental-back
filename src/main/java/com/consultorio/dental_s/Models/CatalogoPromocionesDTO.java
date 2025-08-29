package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoPromocionesDTO {

    private Long idPromocion;
    private String nombre;
    private Integer promocion;
    private String fechaInicio;
    private String fechaTermino;
}
