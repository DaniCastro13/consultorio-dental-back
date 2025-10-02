package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "P_PACIENTES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PACIENTE")
    private Long idPaciente;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "CURP")
    private String curp;

    @Column(name = "CLIENTE_ACTIVO")
    private Boolean clienteActivo;

    @CreationTimestamp
    @Column(name = "FCEHA_ALTA", updatable = false)
    private LocalDateTime fechaAlta;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "ID_MEMBRESIA")
    private Membresias membresia;

    @ManyToOne
    @JoinColumn(name = "ID_SEXO")
    private CatalogoSexo catalogoSexo;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_CIVIL")
    private CatalogoEstadoCivil catalogoEstadoCivil;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_SANGRE")
    private CatalogoTipoSangre catalogoTipoSangre;

    @ManyToMany(mappedBy = "pacientes")
    private List<Auxiliar> auxiliars;
}
