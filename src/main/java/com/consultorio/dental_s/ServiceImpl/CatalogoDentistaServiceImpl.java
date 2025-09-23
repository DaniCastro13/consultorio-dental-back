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
        try {
            log.info("Iniciando el servicio para obtener todos los dentistas");
            List<CatalogoDentistas> listAllDentistas = catalogoDentistasRepository.findAll();
            if (!listAllDentistas.isEmpty()) {
                log.info("Lista de dentistas obtenida {}", listAllDentistas);
                return listAllDentistas;
            }
            log.info("Lista de dentistas obtenida no encontrada");
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener los dentistas: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public CatalogoDentistas updateDentista(Long id, CatalogoDentistas dentista) {
        if(id == null) {
            log.info("El id no puede venir nulo");
            return null;
        }
        return catalogoDentistasRepository.findById(id).map((dentist) -> {
            dentist.setNombreDentista(dentista.getNombreDentista());
            dentist.setApellidoPaterno(dentista.getApellidoPaterno());
            dentist.setApellidoMaterno(dentista.getApellidoMaterno());
            dentist.setCurp(dentista.getCurp());
            dentist.setCorreoElectronico(dentista.getCorreoElectronico());
            dentist.setFechaNacimiento(dentista.getFechaNacimiento());
            dentist.setEdad(dentista.getEdad());
            dentist.setCedulaProfesional(dentista.getCedulaProfesional());
            dentist.setCalle(dentista.getCalle());
            dentist.setNumeroExterior(dentista.getNumeroExterior());
            dentist.setNumeroInterior(dentista.getNumeroInterior());
            dentist.setColonia(dentista.getColonia());
            dentist.setCodigoPostal(dentista.getCodigoPostal());
            dentist.setMunicipio(dentista.getMunicipio());
            dentist.setCiudad(dentista.getCiudad());
            dentist.setUsername(dentista.getUsername());
            dentist.setCatalogoSexo(dentista.getCatalogoSexo());
            dentist.setCatalogoTipoSangre(dentista.getCatalogoTipoSangre());
            dentist.setCatalogoEstadoCivil(dentista.getCatalogoEstadoCivil());
            dentist.setCatalogoRoles(dentista.getCatalogoRoles());
            return catalogoDentistasRepository.save(dentist);
        }).orElseThrow(() -> {
            log.info("No se encontro el dentista con id: {}", id);
            return null;
        });
    }

    @Override
    public boolean deleteDentista(Long id) {
        if(id == null) {
            log.info("El id no puede venir nulo");
            return false;
        }
        return catalogoDentistasRepository.findById(id).map((dentist) -> {
            catalogoDentistasRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> {
            log.info("No se encontro el dentista con id: {}", id);
            return null;
        });
    }
}
