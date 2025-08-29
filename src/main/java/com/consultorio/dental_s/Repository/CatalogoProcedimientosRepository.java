package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoProcedimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoProcedimientosRepository extends JpaRepository<CatalogoProcedimientos, Long> {
}
