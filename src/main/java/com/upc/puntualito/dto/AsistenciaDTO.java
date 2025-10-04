package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDTO {

    private Long id;
    private Long empleadoId;
    private LocalDate fechaRegistro;
    private Instant horaEntrada;
    private Instant horaSalida;
    private String estado;
    private BigDecimal latitudEntrada;
    private BigDecimal longitudEntrada;
    private BigDecimal latiudSalida;
    private BigDecimal longitudsalida;
    private Boolean validacionBiometrica = false;
    private Boolean validacionGeocerca = false;
    private Boolean esEliminado = false;
    private String creadoPor;
    private Instant creadoEn;
    private String modificadoPor;
    private Instant modificadoEn;
}
