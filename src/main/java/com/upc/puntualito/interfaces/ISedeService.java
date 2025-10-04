package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.SedeDTO;

import java.math.BigDecimal;

public interface ISedeService {
    SedeDTO registrar(SedeDTO sedeDTO);
    SedeDTO actualizarUbicacion(Long sedeId, BigDecimal latitud, BigDecimal longitud, BigDecimal radioGeocerca);
}
