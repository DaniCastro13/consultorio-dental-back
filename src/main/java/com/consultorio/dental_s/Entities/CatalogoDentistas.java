package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "CAT_DENTISTAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDentistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DENTISTA")
    private Long idDentista;

    @Column(name = "NOMBRE")
    private String nombreDentista;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "CURP", unique = true)
    private String curp;

    @Column(name = "CORREO_ELECTRONICO", unique = true)
    private String correoElectronico;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "CEDULA_PROFESIONAL")
    private String cedulaProfesional;

    @Column(name = "CALLE")
    private String calle;

    @Column(name = "NUMERO_EXTERIOR")
    private String numeroExterior;

    @Column(name = "NUMERO_INTERIOR")
    private String numeroInterior;

    @Column(name = "COLONIA")
    private String colonia;

    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;

    @Column(name = "MUNICIPIO")
    private String municipio;

    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne
    @JoinColumn(name = "ID_SEXO")
    private CatalogoTipoSangre catalogoSangre;

    @OneToOne
    @JoinColumn(name = "ID_ESTADO_CIVIL")
    private CatalogoEstadoCivil catalogoEstadoCivil;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_SANGRE")
    private CatalogoTipoSangre catalogoTipoSangre;

    @OneToOne
    @JoinColumn(name = "ID_ROL")
    private CatalogoRoles catalogoRoles;
}
