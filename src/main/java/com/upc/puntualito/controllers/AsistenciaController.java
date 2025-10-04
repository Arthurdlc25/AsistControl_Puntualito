package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.dto.AsistenciaDashboardDTO;
import com.upc.puntualito.dto.ReporteMensualDTO;
import com.upc.puntualito.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping("/asistencia")
    public AsistenciaDTO registrarEntrada(@RequestBody AsistenciaDTO asistenciaDTO){
        return asistenciaService.registrarEntrada(asistenciaDTO);
    }

    @PutMapping("/asistencia")
    public AsistenciaDTO registrarSalida(@RequestBody AsistenciaDTO asistenciaDTO){
        return asistenciaService.registrarSalida(asistenciaDTO);
    }

    @GetMapping("/asistencias")
    public List<AsistenciaDTO> listarAsistencias(){
        return asistenciaService.listarAsistencias();
    }

    @GetMapping("/ultima_asistencia/{empleadoId}")
    public AsistenciaDTO ultimaAsistencia(@PathVariable Long empleadoId){
        return asistenciaService.findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);
    }

    @GetMapping("/dashboard/{sedeId}")
    public AsistenciaDashboardDTO obtenerDashboard(@PathVariable Long sedeId) {
        return asistenciaService.obtenerDashboardPorSedeHoy(sedeId);
    }

    @GetMapping("/asistencias_mensuales")
    public List<ReporteMensualDTO> obtenerReporteMensual(@RequestParam Long sedeId, @RequestParam int mes, @RequestParam int anio){
        return asistenciaService.obtenerReporteMensual(sedeId, mes, anio);
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<AsistenciaDTO> obtenerAsistenciasPorEmpleado(@PathVariable Long empleadoId) {
        return asistenciaService.findAsistenciasByEmpleado_Id(empleadoId);
    }
}
