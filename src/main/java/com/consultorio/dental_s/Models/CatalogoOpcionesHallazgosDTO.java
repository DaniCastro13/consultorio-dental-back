package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoOpcionesHallazgosDTO {

    private Long idOpcionHallazgo;
    private String claveHallazgo;
    private String nombreHallazgo;
}
