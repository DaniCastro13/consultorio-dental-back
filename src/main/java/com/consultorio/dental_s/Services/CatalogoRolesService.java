package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoRoles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoRolesService {

    CatalogoRoles saveRol(CatalogoRoles rol);

    List<CatalogoRoles> findAllRoles();

    CatalogoRoles updateRolById(Long id, CatalogoRoles rol);

    boolean deleteRolById(Long id);
}
