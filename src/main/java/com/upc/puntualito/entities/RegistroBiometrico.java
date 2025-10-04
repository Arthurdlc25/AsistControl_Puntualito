package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "registrobiometrico")
public class RegistroBiometrico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(name = "tipobiometria", nullable = false, length = 50)
    private String tipoBiometria;

    @Column(name = "huelladigital", nullable = false)
    private byte[] huellaDigital;

    @Column(name = "modeloiaversion", nullable = false)
    private byte[] modeloIaVersion;

    @Column(name = "imagenreferenciaurl", nullable = false)
    private byte[] imagenReferenciaUrl;

    @Column(name = "fecharegistro", nullable = false)
    private Instant fechaRegistro;

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