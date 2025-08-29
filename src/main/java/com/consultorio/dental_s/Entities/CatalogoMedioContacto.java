package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CAT_MEDIO_CONTACTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoMedioContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAT_MEDIO_CONTACTO")
    private Long idCatalogoMedioContacto;

    @Column(name = "CLAVE")
    private String clave;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;
}
