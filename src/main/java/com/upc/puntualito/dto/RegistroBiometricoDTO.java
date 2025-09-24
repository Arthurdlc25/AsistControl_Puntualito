package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroBiometricoDTO {
    private Long id;
    private Long empleadoId;
    private String tipoBiometria;
    private byte[] huellaDigital;
    private byte[] modeloIaVersion;
    private byte[] imagenReferenciaUrl;
    private Instant fechaRegistro;
    private Boolean esEliminado = false;
    private String creadoPor;
    private Instant creadoEn;
    private String modificadoPor;
    private Instant modificadoEn;
}
