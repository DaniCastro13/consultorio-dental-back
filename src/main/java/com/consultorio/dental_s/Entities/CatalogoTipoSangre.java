package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "CAT_TIPO_SANGRE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoTipoSangre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_SANGRE")
    private Long idTipoSangre;

    @Column(name = "CVE_TIPO_SANGRE", nullable = false)
    private String cveTipoSangre;

    @Column(name = "NOMBRE_TIPO_SANGRE", nullable = false)
    private String nombreTipoSangre;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private Date fechaCreacion;

    @Column(name = "ACTIVO", nullable = false)
    private Boolean activo = true;

}
