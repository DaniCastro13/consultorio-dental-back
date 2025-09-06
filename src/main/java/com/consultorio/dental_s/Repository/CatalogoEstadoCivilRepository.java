package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoEstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoEstadoCivilRepository extends JpaRepository<CatalogoEstadoCivil, Long> {

    @Query("SELECT claveEstadoCivil FROM CatalogoEstadoCivil WHERE claveEstadoCivil=:claveEstadoCivil AND activo=true")
    String findByClave(@Param("claveEstadoCivil") String claveEstadoCivil);

    @Query("SELECT c FROM CatalogoEstadoCivil c WHERE c.activo=true")
    List<CatalogoEstadoCivil> findAllCatalogoEstadoCivilByActive();
}
