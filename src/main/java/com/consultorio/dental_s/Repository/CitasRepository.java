package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long> {
}
