package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoMedioContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoMedioContactoRepository extends JpaRepository<CatalogoMedioContacto, Long> {
}
