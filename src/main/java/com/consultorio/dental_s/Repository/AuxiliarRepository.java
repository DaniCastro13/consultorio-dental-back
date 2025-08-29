package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
}
