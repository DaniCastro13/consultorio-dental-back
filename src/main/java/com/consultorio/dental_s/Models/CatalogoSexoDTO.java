package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoSexoDTO {

    private Long idSexo;
    private String clave;
    private String nombre;
    private Boolean activo;
}
