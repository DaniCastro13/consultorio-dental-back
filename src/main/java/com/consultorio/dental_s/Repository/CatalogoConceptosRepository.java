package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoConceptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoConceptosRepository extends JpaRepository<CatalogoConceptos, Long> {

    @Query("SELECT cc FROM CatalogoConceptos cc WHERE cc.claveGasto=:cveGasto")
    Optional<CatalogoConceptos> findByClaveGasto(@Param("cveGasto") String cveGasto);

    @Query("SELECT cc FROM CatalogoConceptos cc WHERE cc.estatusGasto=true")
    List<CatalogoConceptos> findByGastosActivos();
}
