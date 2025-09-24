package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoPromociones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoPromocionesRepository extends JpaRepository<CatalogoPromociones, Long> {

    @Query("SELECT cp FROM CatalogoPromociones cp WHERE cp.codigoPromocion=:codigoPromocion")
    Optional<CatalogoPromociones> findByNombre(@Param("codigoPromocion") String codigoPromocion);
}
