package com.consultorio.dental_s.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedioContactoDTO {

    private Long idMedioContacto;
    private String valor;
    private Boolean activo;
    private List<CatalogoMedioContactoDTO> catalogoMedioContacto;
}
