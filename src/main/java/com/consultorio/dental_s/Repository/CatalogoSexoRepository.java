package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoSexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoSexoRepository extends JpaRepository<CatalogoSexo, Long> {

    @Query("SELECT cs.clave FROM CatalogoSexo cs WHERE cs.clave=:clave")
    String findByClave(@Param("clave")String clave);

    @Query("SELECT cs FROM CatalogoSexo cs WHERE cs.activo=true")
    List<CatalogoSexo> findAllCatalogoSexoByActive();
}
