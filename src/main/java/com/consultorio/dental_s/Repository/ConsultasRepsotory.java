package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Consultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultasRepsotory extends JpaRepository<Consultas, Long> {
}
