package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoConceptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoConceptosRepository extends JpaRepository<CatalogoConceptos, Long> {
}
