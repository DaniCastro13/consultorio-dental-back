package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import com.consultorio.dental_s.Entities.CatalogoPromociones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembresiasDTO {

    private Long idMembresia;
    private String clave;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private CatalogoDentistas catalogoDentistas;
    private List<CatalogoPromociones> listaPromociones;
}
