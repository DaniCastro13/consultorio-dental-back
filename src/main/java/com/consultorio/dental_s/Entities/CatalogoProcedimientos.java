package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CAT_PROCEDIMIENTOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoProcedimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAT_PROCEDIMIENTO")
    private Long idCatProcedimiento;

    @Column(name = "NOMBRE_PROCEDIMIENTO")
    private String nombreProcedimiento;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "PRECIO_ESTANDAR")
    private Double precioEstandar;

    @Column(name = "PRECIO_ESPECIAL")
    private Double precioEspecial;

    @Column(name = "DURACION_ESTIMADA")
    private Integer duracionEstimada; // Duration in minutes

    @Column(name = "ACTIVO")
    private  Boolean activo;

    @ManyToMany(mappedBy = "catalogoProcedimientos")
    private List<Consultas> consultas;
}
