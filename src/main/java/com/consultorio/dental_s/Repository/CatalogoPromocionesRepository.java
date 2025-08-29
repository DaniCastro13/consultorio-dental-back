package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoPromociones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoPromocionesRepository extends JpaRepository<CatalogoPromociones, Long> {
}
