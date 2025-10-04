package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "horariotrabajo")
public class HorarioTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id", nullable = false, unique = true)
    private Empleado empleado;

    @Column(name = "nombrehorario", nullable = false, length = 100)
    private String nombreHorario;

    @Column(name = "diasemana", nullable = false, length = 10)
    private String diaSemana;

    @Column(name = "horaentrada", nullable = false)
    private LocalTime horaEntrada;

    @Column(name = "horasalida", nullable = false)
    private LocalTime horaSalida;

    @Column(name = "toleranciasalida", nullable = false)
    private Short toleranciaSalida;

    @Column(name = "eseliminado", nullable = false)
    private Boolean esEliminado = false;

    @Column(name = "creadopor", length = 100)
    private String creadoPor;

    @Column(name = "creadoen")
    private Instant creadoEn;

    @Column(name = "modificadopor", length = 100)
    private String modificadoPor;

    @Column(name = "modificadoen")
    private Instant modificadoEn;

}