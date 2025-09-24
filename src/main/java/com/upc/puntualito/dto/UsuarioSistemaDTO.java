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
public class UsuarioSistemaDTO {
    private Long id;
    private Long empleadoId;
    private String nombreDeUsuario;
    private Integer claveHash;
    private String rol;
    private Instant ultimoAcceso;
    private Boolean esEliminado = false;
    private String creadoPor;
    private Instant creadoEn;
    private String modificadoPor;
    private Instant modificadoEn;
}
