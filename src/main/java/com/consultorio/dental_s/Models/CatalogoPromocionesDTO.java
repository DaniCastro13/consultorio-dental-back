package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoPromocionesDTO {

    private Long idPromocion;
    private String nombre;
    private String codigoPromocion;
    private Integer promocion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaTermino;
}
