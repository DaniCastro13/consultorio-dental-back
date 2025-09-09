package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoMedioContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoMedioContactoRepository extends JpaRepository<CatalogoMedioContacto, Long> {

    @Query("SELECT cm.clave FROM CatalogoMedioContacto cm WHERE cm.clave=:clave")
    String findByClave(@Param("clave") String clave);

    @Query("SELECT cm FROM CatalogoMedioContacto cm WHERE cm.activo = true")
    List<CatalogoMedioContacto> findMedioContactoByActivo();
}
