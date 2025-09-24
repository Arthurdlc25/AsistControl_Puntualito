package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.RegistroBiometricoDTO;
import com.upc.puntualito.interfaces.IRegistroBiometricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegistroBiometricoController {
    @Autowired
    private IRegistroBiometricoService iRegistroBiometricoService;

    @PostMapping("/registroBiometrico")
    public RegistroBiometricoDTO registrar(@RequestBody RegistroBiometricoDTO registroBiometricoDTO) {
        return iRegistroBiometricoService.registrar(registroBiometricoDTO);
    }
}
