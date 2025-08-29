package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoSexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoSexoRepository extends JpaRepository<CatalogoSexo, Long> {
}
