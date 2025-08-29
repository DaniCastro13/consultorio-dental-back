package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CAT_ROLES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "CLAVE_ROL",unique = true)
    private String claveRol;

    @Column(name = "NOMBRE_ROL")
    private String nombreRol;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @Column(name = "ACTIVO")
    private Boolean activo;
}
