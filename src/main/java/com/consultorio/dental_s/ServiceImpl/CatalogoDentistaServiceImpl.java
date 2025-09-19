package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Config.SecurityConfig;
import com.consultorio.dental_s.Entities.CatalogoDentistas;
import com.consultorio.dental_s.Exceptions.CatalogoDentistasException;
import com.consultorio.dental_s.Repository.CatalogoDentistasRepository;
import com.consultorio.dental_s.Services.CatalogoDentistasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CatalogoDentistaServiceImpl implements CatalogoDentistasService {

    @Autowired
    private CatalogoDentistasRepository catalogoDentistasRepository;

    @Override
    public CatalogoDentistas saveDetista(CatalogoDentistas dentista) throws CatalogoDentistasException {
        String username = dentista.getUsername();
        String correoElectronico = dentista.getCorreoElectronico();
        Optional<CatalogoDentistas> usernameDB = catalogoDentistasRepository.findByUsername(username);
        if(usernameDB.isPresent()) {
            log.info("Si existe el dentista");
            log.info("Validando si el usuario o el correo esten registrados en base de datos");
            if(usernameDB.get().getUsername().equals(username) || usernameDB.get().getCorreoElectronico().equals(correoElectronico)) {
                throw new CatalogoDentistasException("El usuario o correo ya se encuentran registrados en la base de datos");
            }
        }
        log.info("Creando dentista");
        SecurityConfig securityConfig = new SecurityConfig();
        dentista.setPassword(securityConfig.passwordEncoder().encode(dentista.getPassword()));
        return catalogoDentistasRepository.save(dentista);
    }

    @Override
    public List<CatalogoDentistas> findAllDentistas() {
        return List.of();
    }

    @Override
    public CatalogoDentistas updateDentista(Long id, CatalogoDentistas dentista) {
        return null;
    }

    @Override
    public boolean deleteDentista(Long id) {
        return false;
    }
}
