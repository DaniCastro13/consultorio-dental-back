package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MEDIO_CONTACTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedioContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDIO_CONTACTO")
    private Long idMedioContacto;

    @Column(name = "VALOR")
    private String valor;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @OneToMany
    @JoinColumn(name = "ID_CAT_MEDIO_CONTACTO")
    private List<CatalogoMedioContacto> catalogoMedioContacto;
}
