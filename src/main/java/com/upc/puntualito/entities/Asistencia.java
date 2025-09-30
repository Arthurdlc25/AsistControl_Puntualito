package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(name = "fecharegistro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "horaentrada", nullable = false)
    private Instant horaEntrada;

    @Column(name = "horasalida")
    private Instant horaSalida;

    @Column(name = "estado")
    private String estado;

    @Column(name = "latitudentrada", nullable = false, precision = 11, scale = 8)
    private BigDecimal latitudEntrada;

    @Column(name = "longitudentrada", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitudEntrada;

    @Column(name = "latiudsalida", nullable = false, precision = 11, scale = 8)
    private BigDecimal latiudSalida;

    @Column(name = "longitudsalida", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitudSalida;

    @Column(name = "validacionbiometrica", nullable = false)
    private Boolean validacionBiometrica = false;

    @Column(name = "validaciongeocerca", nullable = false)
    private Boolean validacionGeocerca = false;

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