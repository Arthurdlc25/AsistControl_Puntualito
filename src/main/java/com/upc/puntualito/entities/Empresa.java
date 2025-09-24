package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ruc", nullable = false, length = 20)
    private String ruc;

    @Column(name = "razonsocial", nullable = false)
    private String razonSocial;

    @Column(name = "nombrecomercial", nullable = false)
    private String nombreComercial;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "correoelectronico", nullable = false)
    private String correoElectronico;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "maximoempleado", nullable = false)
    private Integer maximoEmpleado;

    @Column(name = "eseliminado", nullable = false)
    private Boolean esEliminado = false;

    @Column(name = "creadopor", nullable = false)
    private String creadoPor;

    @Column(name = "creadoen", nullable = false)
    private LocalDate creadoEn;

    @Column(name = "modificadorpor", nullable = false)
    private String modificadorPor;

    @Column(name = "modificadoen", nullable = false)
    private LocalDate modificadoEn;

}