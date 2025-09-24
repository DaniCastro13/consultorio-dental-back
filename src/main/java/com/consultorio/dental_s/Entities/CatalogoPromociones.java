package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CAT_PROMOCIONES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogoPromociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROMOCION")
    private Long idPromocion;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CODIGO_PROMOCION")
    private String codigoPromocion;

    @Column(name = "PROMOCION")
    private Integer promocion;

    @CreationTimestamp
    @Column(name = "FECHA_REGISTRO")
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_INICIO")
    private LocalDateTime fechaInicio;

    @Column(name = "FECHA_TERMINO")
    private LocalDateTime fechaTermino;
}
