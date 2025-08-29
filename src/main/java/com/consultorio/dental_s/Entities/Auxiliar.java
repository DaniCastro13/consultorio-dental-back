package com.consultorio.dental_s.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "P_AUXILIAR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auxiliar {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "ID_AUXILIAR")
    private Long idAuxiliar;

    @OneToOne
    @JoinColumn(name = "ID_PACIENTE")
    private Pacientes paciente;

    @Column(name = "PARENTESCO")
    private String parentesco;

    @ManyToMany
    @JoinTable(
        name = "AUXILIAR_PACIENTE",
        joinColumns = @JoinColumn(name = "ID_AUXILIAR"),
        inverseJoinColumns = @JoinColumn(name = "ID_PACIENTE")
    )
    private List<Pacientes> pacientes;

}
