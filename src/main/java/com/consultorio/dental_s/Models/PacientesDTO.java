package com.consultorio.dental_s.Models;

import com.consultorio.dental_s.Entities.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacientesDTO {

    private Long idPaciente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String telefono;
    private String curp;
    private Boolean clienteActivo;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaActualizacion;
    private Membresias membresia;
    private CatalogoSexo catalogoSexo;
    private CatalogoEstadoCivil catalogoEstadoCivil;
    private CatalogoTipoSangre catalogoTipoSangre;
}
