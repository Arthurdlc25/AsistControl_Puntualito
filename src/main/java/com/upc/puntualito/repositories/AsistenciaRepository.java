package com.upc.puntualito.repositories;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    @Query("SELECT a FROM Asistencia a WHERE a.empleado.id = :empleadoId ORDER BY a.fechaRegistro DESC, a.horaEntrada DESC")
    Asistencia findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(@Param("empleadoId") Long empleadoId);
}
