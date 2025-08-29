package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "P_CITAS")
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA")
    private Long idCita;

    @Column(name = "FECHA_CITA", nullable = false)
    private LocalDateTime fechaCita;

    @Column(name = "HORA_CITA", nullable = false)
    private Date horaCita;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "ASISTENCIA", nullable = false)
    private Boolean asistencia;

    @ManyToOne
    @JoinColumn(name = "ID_DENTISTA", nullable = false)
    private CatalogoDentistas dentista;

    @ManyToOne
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    private Pacientes paciente;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;
}
