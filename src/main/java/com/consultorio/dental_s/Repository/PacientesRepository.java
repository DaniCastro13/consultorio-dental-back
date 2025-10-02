package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacientesRepository extends JpaRepository<Pacientes, Long> {

    @Query("SELECT p FROM Pacientes p WHERE p.curp=:curp")
    Optional<Pacientes> findByCurp(@Param("curp") String curp);

    @Query("SELECT p FROM Pacientes p WHERE p.clienteActivo=true")
    List<Pacientes> findPacientesByActivo();
}
