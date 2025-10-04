package com.upc.puntualito.controllers;

import com.upc.puntualito.dto.SedeDTO;
import com.upc.puntualito.services.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class SedeController {
    @Autowired
    private SedeService sedeService;

    @PostMapping("/sede")
    public SedeDTO registrar(@RequestBody SedeDTO sedeDTO) {
        return sedeService.registrar(sedeDTO);
    }
    @PutMapping("/{id}/ubicacion")
    public SedeDTO actualizarUbicacion(@PathVariable Long id, @RequestParam BigDecimal latitud, @RequestParam BigDecimal longitud, @RequestParam BigDecimal radioGeocerca) {
        return sedeService.actualizarUbicacion(id, latitud, longitud, radioGeocerca);
    }
}
