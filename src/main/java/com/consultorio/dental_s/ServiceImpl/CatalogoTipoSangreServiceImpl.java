package com.consultorio.dental_s.ServiceImpl;

import com.consultorio.dental_s.Entities.CatalogoTipoSangre;
import com.consultorio.dental_s.Exceptions.CatalagoTipoSangreException;
import com.consultorio.dental_s.Repository.CatalogoTipoSangreRepository;
import com.consultorio.dental_s.Services.CatalogoTipoSangreService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoTipoSangreServiceImpl implements CatalogoTipoSangreService {

    private final CatalogoTipoSangreRepository catalogoTipoSangreRepository;

    public CatalogoTipoSangreServiceImpl(CatalogoTipoSangreRepository catalogoTipoSangreRepository) {
        this.catalogoTipoSangreRepository = catalogoTipoSangreRepository;
    }

    @Transactional
    @Override
    public CatalogoTipoSangre saveCatalogoTipoSangre(CatalogoTipoSangre catalogoTipoSangre) {
        Optional<CatalogoTipoSangre> existingTipoSangre = catalogoTipoSangreRepository.findByName(catalogoTipoSangre.getCveTipoSangre(), catalogoTipoSangre.getNombreTipoSangre());
        if(existingTipoSangre.isPresent()) {
            throw new CatalagoTipoSangreException("Tipo de sangre ya existe");
        }
        return catalogoTipoSangreRepository.save(catalogoTipoSangre);
    }

    @Override
    public List<CatalogoTipoSangre> findAllCatalogoTipoSangre() {
        return List.of();
    }

    @Override
    public Optional<CatalogoTipoSangre> findCatalogoTipoSangreById(Long idTipoSangre) {
        return Optional.empty();
    }

    @Override
    public Optional<CatalogoTipoSangre> updateCatalogoTipoSangre(Long idTipoSangre, CatalogoTipoSangre catalogoTipoSangre) {
        return Optional.empty();
    }

    @Override
    public void deleteCatalogoTipoSangre(Long idTipoSangre) {

    }
}
