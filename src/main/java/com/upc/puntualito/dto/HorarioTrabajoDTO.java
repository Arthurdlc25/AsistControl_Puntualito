package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioTrabajoDTO {
    private Long id;
    private Long empleadoId;
    private String nombreHorario;
    private String diaSemana;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Short toleranciaSalida;
    private Boolean esEliminado = false;
    private String creadoPor;
    private LocalDate creadoEn;
    private String modificadoPor;
    private LocalDate modificadoEn;
}
