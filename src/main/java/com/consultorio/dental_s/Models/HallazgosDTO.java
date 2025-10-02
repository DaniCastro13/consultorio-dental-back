package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.CatalogoOpcionesHallazgos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallazgosDTO {

    private Long idHallazgo;
    private String clave;
    private String descripcion;
    private String observaciones;
    private String rutaImagen;
    private CatalogoOpcionesHallazgos catalogoOpcionesHallazgos;
}
