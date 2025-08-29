package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.MedioContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioContactoRepository extends JpaRepository<MedioContacto, Long> {
}
