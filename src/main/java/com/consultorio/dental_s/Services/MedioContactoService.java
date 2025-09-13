package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.MedioContacto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedioContactoService {

    MedioContacto saveContacto(MedioContacto medioContacto);

    List<MedioContacto> findAllContacto();

    MedioContacto updateContacto(Long id, MedioContacto medioContacto);

    void deleteContactoById(Long id);
}
