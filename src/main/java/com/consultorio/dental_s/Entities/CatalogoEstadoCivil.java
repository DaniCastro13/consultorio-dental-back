package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CAT_ESTADO_CIVIL")
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoEstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_CIVIL")
    private Long idCatalogoEstadoCivil;

    @Column(name = "CLAVE_ESTADO_CIVIL")
    private String claveEstadoCivil;

    @Column(name = "NOMBRE_ESTADO_CIVIL")
    private String nombreEstadoCivil;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;
}
