package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.interfaces.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AsistenciaController {
    @Autowired
    IAsistenciaService asistenciaService;

    @PostMapping("/asistencia")
    public AsistenciaDTO registrar(AsistenciaDTO asistenciaDTO){
        return asistenciaService.registrar(asistenciaDTO);
    }

    @PutMapping("/asistencia")
    public AsistenciaDTO actualizar(AsistenciaDTO asistenciaDTO){
        return asistenciaService.actualizar(asistenciaDTO);
    }

    @GetMapping("/asistencias")
    public List<AsistenciaDTO> listarAsistencias(){
        return asistenciaService.listarAsistencias();
    }
}
