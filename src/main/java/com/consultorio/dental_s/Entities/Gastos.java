package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "P_GASTOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GASTO")
    private Long idGasto;

    @CreationTimestamp
    @Column(name = "FECHA_GASTO")
    private LocalDateTime fechaGasto;

    @Column(name = "IMPORTE")
    private Double importe;

    @OneToMany
    @JoinColumn(name = "ID_CAT_GASTO")
    private List<CatalogoConceptos> catalogoConceptos;

    @Column(name = "JUSTIFICACION_GASTO")
    private String justificacionGasto;

    @Column(name = "OBSERVACIONES")
    private String observaciones;
}
