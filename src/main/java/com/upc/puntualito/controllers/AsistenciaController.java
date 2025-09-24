package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.interfaces.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AsistenciaController {
    @Autowired
    private IAsistenciaService iAsistenciaService;

    @PostMapping("/asistencia")
    public AsistenciaDTO registrar(@RequestBody AsistenciaDTO asistenciaDTO){
        return iAsistenciaService.registrar(asistenciaDTO);
    }

    @PutMapping("/asistencia")
    public AsistenciaDTO actualizar(@RequestBody AsistenciaDTO asistenciaDTO){
        return iAsistenciaService.actualizar(asistenciaDTO);
    }

    @GetMapping("/asistencias")
    public List<AsistenciaDTO> listarAsistencias(){
        return iAsistenciaService.listarAsistencias();
    }
}
