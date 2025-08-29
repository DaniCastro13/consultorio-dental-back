package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "REL_MEDIO_CONTACTO_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RelMedioContactoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REL_MEDIO_CONTACTO_USUARIO")
    private Long idRelMedioContactoUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_MEDIO_CONTACTO", nullable = false)
    private MedioContacto medioContacto;

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "IS_DENTISTA")
    private Boolean isDentista;
}
