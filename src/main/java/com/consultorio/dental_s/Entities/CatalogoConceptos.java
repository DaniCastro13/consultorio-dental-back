package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CAT_GASTOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoConceptos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAT_GASTO")
    private Long idCatGasto;

    @Column(name = "CLAVE_GASTO")
    private String claveGasto;

    @Column(name = "NOMBRE_GASTO")
    private String nombreGasto;

    @Column(name = "ESTATUS_GASTO")
    private Boolean estatusGasto;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
}
