package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoOpcionesHallazgos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoOpcionesHallazgosRepository extends JpaRepository<CatalogoOpcionesHallazgos, Long> {

    @Query("SELECT coh FROM CatalogoOpcionesHallazgos coh WHERE coh.claveHallazgo=:cveHallazgo")
    Optional<CatalogoOpcionesHallazgos> findByClave(@Param("cveHallazgo") String cveHallazgo);

}
