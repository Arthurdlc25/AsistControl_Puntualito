package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    @Column(name = "codigoempleado", nullable = false, length = 50)
    private String codigoEmpleado;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "fotoperfilurl", nullable = false, length = 100)
    private String fotoPerfilUrl;

    @Column(name = "creadopor", nullable = false)
    private String creadoPor;

    @Column(name = "creadoen", nullable = false)
    private LocalDate creadoEn;

    @Column(name = "modificadopor", nullable = false, length = 100)
    private String modificadoPor;

    @Column(name = "modificadoen", nullable = false)
    private LocalDate modificadoEn;

}