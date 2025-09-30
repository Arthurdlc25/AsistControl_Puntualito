package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.HorarioTrabajoDTO;
import com.upc.puntualito.services.HorarioTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HorarioTrabajoController {
    @Autowired
    private HorarioTrabajoService horarioTrabajoService;

    @PostMapping("/horarioTrabajo")
    public HorarioTrabajoDTO registrar(@RequestBody HorarioTrabajoDTO horarioTrabajoDTO) {
        return horarioTrabajoService.registrar(horarioTrabajoDTO);
    }

    @PutMapping("/horarioTrabajo")
    public HorarioTrabajoDTO actualizarHorario(@RequestBody HorarioTrabajoDTO horarioTrabajoDTO) {
        return horarioTrabajoService.actualizarHorario(horarioTrabajoDTO);
    }

    @GetMapping("/horarioEmpleado")
    public HorarioTrabajoDTO obtenerHorarioEmpleado(@RequestParam Long id){
        return horarioTrabajoService.findByEmpleadoId(id);
    }
}
