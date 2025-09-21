package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRolesRepository extends JpaRepository<CatalogoRoles, Long> {

    @Query("SELECT cr FROM CatalogoRoles cr WHERE cr.claveRol=:clave ORDER BY cr.fechaCreacion ASC")
    Optional<CatalogoRoles> findBYClave(@Param("clave") String clave);

    @Query("SELECT crl FROM CatalogoRoles crl WHERE crl.activo = true")
    List<CatalogoRoles> findByAllActive();

}
