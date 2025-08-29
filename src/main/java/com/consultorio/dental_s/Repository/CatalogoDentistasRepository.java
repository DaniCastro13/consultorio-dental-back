package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoDentistasRepository extends JpaRepository<CatalogoDentistas, Long> {
}
