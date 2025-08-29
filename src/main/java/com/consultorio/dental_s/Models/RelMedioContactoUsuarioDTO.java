package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelMedioContactoUsuarioDTO {

    private Long idRelMedioContactoUsuario;
    private MedioContactoDTO medioContactoDTO;
    private CatalogoDentistasDTO catalogoDentistasDTO;
    private Boolean activo;
}
