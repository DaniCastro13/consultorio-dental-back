package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "P_MEMBRESIA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membresias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEMBRESIA")
    private Long idMembresia;

    @Column(name = "CLAVE", unique = true)
    private String clave;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "ID_DENTISTA")
    private CatalogoDentistas dentista;

    @ManyToOne
    @JoinColumn(name = "ID_PROMOCION")
    private CatalogoPromociones promocion;
}
