package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.CatalogoDentistas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogoDentistasService {

    CatalogoDentistas saveDetista(CatalogoDentistas dentista);

    List<CatalogoDentistas> findAllDentistas();

    CatalogoDentistas updateDentista(Long id, CatalogoDentistas dentista);

     boolean deleteDentista(Long id);
}
