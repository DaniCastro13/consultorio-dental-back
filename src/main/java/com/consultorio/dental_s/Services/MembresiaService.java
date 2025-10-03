package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Membresias;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembresiaService {

    Membresias saveMembresia(Membresias membresias);

    List<Membresias> findAllMembresias();

    Membresias udateMembresiaById(Long idMembresia, Membresias membresias);

    boolean deleteMembresiaById(Long idMembresia);
}
