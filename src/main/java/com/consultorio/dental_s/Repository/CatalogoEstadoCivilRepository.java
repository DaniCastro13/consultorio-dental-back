package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoEstadoCivilRepository extends JpaRepository<CatalogoEstadoCivil, Long> {
}
