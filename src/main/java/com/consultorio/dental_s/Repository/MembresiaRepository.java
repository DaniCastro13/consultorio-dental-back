package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Membresias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresias, Long> {
}
