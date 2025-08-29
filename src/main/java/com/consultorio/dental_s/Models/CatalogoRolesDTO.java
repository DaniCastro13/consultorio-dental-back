package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoRolesDTO {

    private Long idRol;
    private String claveRol;
    private String nombreRol;
    private Boolean activo;
}
