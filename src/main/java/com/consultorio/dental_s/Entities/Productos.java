package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "P_PRODUCTOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    private Long idProducto;

    @Column(name = "CLAVE_PRODUCTO")
    private String claveProducto;

    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;

    @Column(name = "CODIGO_BARRAS", unique = true)
    private String codigoBarras;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CARACTERISTICAS")
    private String caracteristicas;

    @Column(name = "MAXIMO")
    private Integer maximo;

    @Column(name = "MINIMO")
    private Integer minimo;

    @Column(name = "INDICACIONES_USO")
    private String indicacionesUso;

    @Column(name = "CONTRAINDICACIONES")
    private String contraindicaciones;

    @Column(name = "NOTA")
    private String nota;

    @CreationTimestamp
    @Column(name = "FECHA_ALTA", updatable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @Column(name = "PRECIO_VENTA")
    private Double precioVenta;

    @Column(name = "PRECIO_ESPECIAL")
    private Double precioEspecial;

    @Column(name = "PROMOCION_APLICADA")
    private String promocionAplicada;
}
