package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.EmpleadoDTO;
import com.upc.puntualito.interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmpleadoController {
    @Autowired
    private IEmpleadoService iEmpleadoService;

    @PostMapping("/empleado")
    public EmpleadoDTO registrar(@RequestBody EmpleadoDTO empleadoDTO){
        return iEmpleadoService.registrar(empleadoDTO);
    }
}
