package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.EmpleadoDTO;
import com.upc.puntualito.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/empleado")
    public EmpleadoDTO registrar(@RequestBody EmpleadoDTO empleadoDTO){
        return empleadoService.registrar(empleadoDTO);
    }
    @PutMapping("/empleado")
    public EmpleadoDTO actualizar(@RequestBody EmpleadoDTO empleadoDTO){
        return empleadoService.actualizar(empleadoDTO);
    }
}
