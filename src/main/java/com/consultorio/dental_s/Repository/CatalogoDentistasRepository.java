package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoDentistasRepository extends JpaRepository<CatalogoDentistas, Long> {

    @Query("SELECT c FROM CatalogoDentistas c WHERE c.username=:username")
    Optional<CatalogoDentistas> findByUsername(@Param("username") String username);

}
