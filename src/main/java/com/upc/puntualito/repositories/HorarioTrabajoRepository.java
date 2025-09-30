package com.upc.puntualito.repositories;

import com.upc.puntualito.dto.HorarioTrabajoDTO;
import com.upc.puntualito.entities.HorarioTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioTrabajoRepository extends JpaRepository<HorarioTrabajo, Long> {
    HorarioTrabajo findByEmpleadoId(Long empleadoId);
}
