package com.consultorio.dental_s.Repository;

import com.consultorio.dental_s.Entities.RelMedioContactoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelMedioContactoUsuarioRepository extends JpaRepository<RelMedioContactoUsuario, Long> {
}
