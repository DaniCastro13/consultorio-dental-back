package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.MedioContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedioContactoRepository extends JpaRepository<MedioContacto, Long> {

    @Query("SELECT m.valor FROM MedioContacto m WHERE m.valor=:valor")
    String findyByClave(@Param("valor") String valor);

    @Query("SELECT mc, cm.nombre FROM MedioContacto mc INNER JOIN CatalogoMedioContacto cm ON mc.catalogoMedioContacto.idCatalogoMedioContacto = cm.idCatalogoMedioContacto WHERE mc.activo = true AND cm.activo = true")
    List<MedioContacto> findAlldMedioContacto();

}
