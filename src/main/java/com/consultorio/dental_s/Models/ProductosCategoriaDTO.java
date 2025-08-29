package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosCategoriaDTO {

    private Long idProductosCategoria;
    private String claveCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Boolean activo;
}
