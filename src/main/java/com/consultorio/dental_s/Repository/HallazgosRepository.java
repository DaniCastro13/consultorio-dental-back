package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Hallazgos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallazgosRepository extends JpaRepository<Hallazgos, Long> {
}
