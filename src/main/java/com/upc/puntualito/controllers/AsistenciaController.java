package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.AsistenciaDTO;
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
}
