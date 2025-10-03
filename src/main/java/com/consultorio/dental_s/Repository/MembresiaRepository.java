package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.Membresias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresias, Long> {

    @Query("SELECT m FROM Membresias m WHERE m.clave=:clave")
    Optional<Membresias> findByClave(@Param("clave") String clave);

    @Query("SELECT m FROM Membresias m WHERE m.activo=true")
    List<Membresias> findAllMembresiasActivas();
}
