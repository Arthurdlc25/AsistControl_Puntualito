package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.EmpresaDTO;
import com.upc.puntualito.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/empresa")
    public EmpresaDTO registrar(@RequestBody EmpresaDTO empresaDTO) {
        return empresaService.registrar(empresaDTO);
    }
}
