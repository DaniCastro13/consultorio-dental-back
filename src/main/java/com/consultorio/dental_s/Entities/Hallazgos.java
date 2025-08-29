package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "P_HALLAZGOS")
public class Hallazgos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HALLAZGO")
    private Long idHallazgo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "RUTA_IMAGEN")
    private String rutaImagen;

    @ManyToOne
    @JoinColumn(name = "ID_OPCION_HALLAZGO")
    private CatalogoOpcionesHallazgos opcionHallazgo;

    @ManyToMany(mappedBy = "hallazgos")
    private List<Consultas> consultas;
}
