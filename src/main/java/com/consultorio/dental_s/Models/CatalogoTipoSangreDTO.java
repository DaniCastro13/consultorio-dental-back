package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoTipoSangreDTO {

    private Long idTipoSangre;
    private String cveTipoSangre;
    private String nombreTipoSangre;
    private Boolean activo;
}
