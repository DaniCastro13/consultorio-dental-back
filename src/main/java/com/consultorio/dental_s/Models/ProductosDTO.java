package com.consultorio.dental_s.Models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosDTO {

    private Long idProducto;
    private String claveProducto;
    private String nombreProducto;
    private String codigoBarras;
    private String descripcion;
    private String caracteristicas;
    private Integer maximo;
    private Integer minimo;
    private String indicacionesUso;
    private String contraindicaciones;
    private String nota;
    private LocalDateTime fechaAlta;
    private Boolean activo;
    private Double precioVenta;
    private Double precioEspecial;
    private String promocionAplicada;
}
