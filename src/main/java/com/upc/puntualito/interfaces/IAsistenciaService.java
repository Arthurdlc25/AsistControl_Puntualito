package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.entities.Asistencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface IAsistenciaService {
    AsistenciaDTO registrarEntrada(AsistenciaDTO asistenciaDTO);
    AsistenciaDTO registrarSalida(AsistenciaDTO asistenciaDTO);
    List<AsistenciaDTO> listarAsistencias();
    AsistenciaDTO findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(Long empleadoId);
}
