package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {
    private Long id;
    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaRegistro;
    private Integer maximoEmpleado;
    private Boolean esEliminado = false;
    private String creadoPor;
    private Instant creadoEn;
    private String modificadoPor;
    private Instant modificadoEn;
}
