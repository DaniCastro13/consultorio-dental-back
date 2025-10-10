package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Citas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CitasService {

    Citas saveCita(Citas citas);

    List<Citas> getAllCitas();

    Citas updateCita(Long idCita, Citas citas);

    boolean deleteCita(Long idCita);
}
