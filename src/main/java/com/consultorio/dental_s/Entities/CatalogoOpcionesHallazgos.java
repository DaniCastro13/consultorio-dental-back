package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CAT_OPCIONES_HALLAZGOS")
public class CatalogoOpcionesHallazgos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OPCION_HALLAZGO")
    private Long idOpcionHallazgo;

    @Column(name = "CLAVE_HALLAZGO", unique = true)
    private String claveHallazgo;

    @Column(name = "NOMBRE_HALLAZGO")
    private String nombreHallazgo;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
}
