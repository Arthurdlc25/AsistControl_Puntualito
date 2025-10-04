package com.upc.puntualito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteMensualDTO {

    //HU09

    private String nombreEmpleado;
    private Long diasTrabajados;
    private double horasTotales;
    private Long tardanzas;
    private Long faltas;
}
