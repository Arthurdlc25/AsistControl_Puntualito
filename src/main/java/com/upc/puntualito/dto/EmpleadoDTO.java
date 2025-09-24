package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {
    private Long id;
    private Long empresaId;
    private Long sedeId;
    private String codigoEmpleado;
    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String cargo;
    private String fotoPerfilUrl;
    private String creadoPor;
    private LocalDate creadoEn;
    private String modificadoPor;
    private LocalDate modificadoEn;
}
