package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.SedeDTO;
import com.upc.puntualito.interfaces.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SedeController {
    @Autowired
    private ISedeService isedeService;

    @PostMapping("/sede")
    public SedeDTO registrar(@RequestBody SedeDTO sedeDTO) {
        return isedeService.registrar(sedeDTO);
    }
}
