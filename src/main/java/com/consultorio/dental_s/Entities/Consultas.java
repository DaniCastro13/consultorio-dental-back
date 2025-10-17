package com.consultorio.dental_s.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "P_CONSULTAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private Long idConsulta;

    @Column(name = "ALERGICO")
    private String alergico;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "ESTATURA")
    private Double estatura;

    @Column(name = "PADECIMIENTO")
    private String padecimiento;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "ID_CITA")
    private Citas cita;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "CONSULTA_PROCEDIMIENTOS",
            joinColumns = @JoinColumn(name = "ID_CONSULTA"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROCEDIMIENTO")
    )
    private List<CatalogoProcedimientos> catalogoProcedimientos;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "CONSULTA_HALLAZGOS",
            joinColumns = @JoinColumn(name = "ID_CONSULTA"),
            inverseJoinColumns = @JoinColumn(name = "ID_HALLAZGO")
    )
    private List<Hallazgos> hallazgos;
}
