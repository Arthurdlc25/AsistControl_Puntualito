package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.EmpleadoDTO;
import com.upc.puntualito.dto.HorarioTrabajoDTO;
import com.upc.puntualito.entities.HorarioTrabajo;

public interface IHorarioTrabajoService {
    HorarioTrabajoDTO registrar(HorarioTrabajoDTO horarioTrabajoDTO);
    HorarioTrabajoDTO actualizarHorario(HorarioTrabajoDTO dto);
    HorarioTrabajoDTO findByEmpleadoId(Long empleadoId);
}
