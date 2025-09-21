package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import com.consultorio.dental_s.Entities.CatalogoRoles;
import com.consultorio.dental_s.Entities.CatalogoSexo;
import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDentistasDTO {

    private Long idDentista;
    private String nombreDentista;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String correoElectronico;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private String cedulaProfesional;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String codigoPostal;
    private String municipio;
    private String ciudad;
    private String username;
    private String password;
    private CatalogoSexoDTO catalogoSexo;
    private CatalogoTipoSangreDTO catalogoTipoSangre;
    private CatalogoEstadoCivilDTO catalogoEstadoCivil;
    private CatalogoRolesDTO catalogoRoles;
}
