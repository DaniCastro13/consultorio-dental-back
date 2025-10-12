package com.consultorio.dental_s.Services;

import com.consultorio.dental_s.Entities.Gastos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GastosService {

    Gastos saveGasto(Gastos gasto);

    List<Gastos> getAllGastos();

    Gastos updateGastoById(Long idGasto, Gastos gasto);

    boolean deleteGastoById(Long idGasto);
}
