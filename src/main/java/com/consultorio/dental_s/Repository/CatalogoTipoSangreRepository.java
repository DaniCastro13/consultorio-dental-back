package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoTipoSangreRepository extends JpaRepository<CatalogoTipoSangre, Long> {

    @Query("SELECT ct FROM CatalogoTipoSangre ct WHERE ct.activo=true")
    List<CatalogoTipoSangre> findAllCatalogoTipoSangre();

    @Query("SELECT C FROM CatalogoTipoSangre C WHERE C.cveTipoSangre =:cveTipoSangre OR C.nombreTipoSangre =:nombreTipoSangre")
    Optional<CatalogoTipoSangre> findByName(@Param("cveTipoSangre") String cveTipoSangre, @Param("nombreTipoSangre") String nombreTipoSangre);
}
