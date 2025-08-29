package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRolesRepository extends JpaRepository<CatalogoRoles, Long> {
}
