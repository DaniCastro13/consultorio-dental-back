package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientesRepository extends JpaRepository<Pacientes, Long> {
}
