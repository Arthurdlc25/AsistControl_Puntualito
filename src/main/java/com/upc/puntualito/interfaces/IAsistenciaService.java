package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.dto.AsistenciaDashboardDTO;
import com.upc.puntualito.dto.ReporteMensualDTO;
import com.upc.puntualito.entities.Asistencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface IAsistenciaService {
    AsistenciaDTO registrarEntrada(AsistenciaDTO asistenciaDTO);
    AsistenciaDTO registrarSalida(AsistenciaDTO asistenciaDTO);
    List<AsistenciaDTO> listarAsistencias();
    AsistenciaDTO findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(Long empleadoId);
    AsistenciaDashboardDTO obtenerDashboardPorSedeHoy(Long sedeId);
    List<ReporteMensualDTO> obtenerReporteMensual(Long sedeId, int mes, int año);
    List<AsistenciaDTO> findAsistenciasByEmpleado_Id(Long empleadoId);
}
