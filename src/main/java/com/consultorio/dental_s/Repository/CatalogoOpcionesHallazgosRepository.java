package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoOpcionesHallazgos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoOpcionesHallazgosRepository extends JpaRepository<CatalogoOpcionesHallazgos, Long> {
}
