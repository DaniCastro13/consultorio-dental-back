package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PRODUCTOS_CATEGORIA")
@AllArgsConstructor
@NoArgsConstructor
public class ProductosCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTOS_CATEGORIA")
    private Long idCategoria;

    @Column(name = "CLAVE_CATEGORIA")
    private String claveCategoria;

    @Column(name = "NOMBRE_CATEGORIA")
    private String nombreCategoria;

    @Column(name = "DESCRIPCION_CATEGORIA")
    private String descripcionCategoria;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;
}
