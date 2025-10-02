package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Hallazgos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallazgosRepository extends JpaRepository<Hallazgos, Long> {

    @Query("SELECT h FROM Hallazgos h WHERE h.clave=:clave")
    Optional<Hallazgos> findByClave(@Param("clave") String clave);
}
