package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long> {
}
