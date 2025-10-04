package com.upc.puntualito.repositories;

import com.upc.puntualito.dto.AsistenciaDashboardDTO;
import com.upc.puntualito.dto.ReporteMensualDTO;
import com.upc.puntualito.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    @Query("SELECT a FROM Asistencia a WHERE a.empleado.id = :empleadoId ORDER BY a.fechaRegistro DESC, a.horaEntrada DESC")
    Asistencia findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(@Param("empleadoId") Long empleadoId);

    @Query("SELECT a FROM Asistencia a " + "WHERE a.empleado.sede.id = :sedeId " + "AND a.fechaRegistro = CURRENT_DATE")
    List<Asistencia> findAsistenciasDelDiaPorSede(@Param("sedeId") Long sedeId);

    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.sede.id = :sedeId")
    int countEmpleadosPorSede(@Param("sedeId") Long sedeId);

    @Query("SELECT new com.upc.puntualito.dto.AsistenciaDashboardDTO(" +
            "COUNT(CASE WHEN a.estado = 'presente' THEN 1 END), " +
            "COUNT(CASE WHEN a.estado = 'ausente' THEN 1 END), " +
            "COUNT(CASE WHEN a.estado = 'tardanza' THEN 1 END)) " +
            "FROM Asistencia a " +
            "JOIN a.empleado e " +
            "WHERE a.fechaRegistro = CURRENT_DATE() " +
            "AND e.sede = :sedeId " +
            "AND a.esEliminado = false")
    AsistenciaDashboardDTO obtenerDashboardPorSedeHoy(@Param("sedeId") Long sedeId);

    @Query(value = """
        SELECT 
            CONCAT(e.nombres, ' ', e.apellidos) AS nombreEmpleado,
            COUNT(CASE WHEN a.horaentrada IS NOT NULL AND a.horasalida IS NOT NULL THEN 1 END) AS diasTrabajados,
            COALESCE(SUM(CASE WHEN a.horaentrada IS NOT NULL AND a.horasalida IS NOT NULL 
                THEN (TIMESTAMPDIFF(SECOND, a.horaentrada, a.horasalida) / 3600.0) 
                ELSE 0 END), 0) AS horasTotales,
            COUNT(CASE WHEN a.estado = 'tardanza' THEN 1 END) AS tardanzas,
            COUNT(CASE WHEN a.estado = 'ausente' THEN 1 END) AS faltas
        FROM empleado e 
        LEFT JOIN asistencia a ON a.empleado_id = e.id 
            AND a.eseliminado = false 
            AND YEAR(a.fecharegistro) = :año 
            AND MONTH(a.fecharegistro) = :mes
        WHERE e.sede_id = :sedeId
        GROUP BY e.id, e.nombres, e.apellidos 
        ORDER BY e.nombres, e.apellidos
        """,
            nativeQuery = true)
    List<ReporteMensualDTO> obtenerReporteMensual(@Param("sedeId") Long sedeId, @Param("mes") int mes, @Param("año") int año);

    List<Asistencia> findAsistenciasByEmpleado_Id(Long empleadoId);
}
