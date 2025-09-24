package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {
    private Long id;
    private Long empresaId;
    private String codigo;
    private String nombre;
    private String direccion;
    private String distrito;
    private String provincia;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private BigDecimal radioGeocerca;
    private Boolean esEliminado = false;
    private String creadoPor;
    private LocalDate creandoEn;
    private String modificadoPor;
    private LocalDate modificadoEn;
}
