package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sede")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "distrito", nullable = false, length = 100)
    private String distrito;

    @Column(name = "provincia", nullable = false, length = 100)
    private String provincia;

    @Column(name = "latitud", nullable = false, precision = 11, scale = 8)
    private BigDecimal latitud;

    @Column(name = "longitud", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitud;

    @Column(name = "radiogeocerca", nullable = false, precision = 8, scale = 2)
    private BigDecimal radioGeocerca;

    @Column(name = "eseliminado", nullable = false)
    private Boolean esEliminado = false;

    @Column(name = "creadopor")
    private String creadoPor;

    @Column(name = "creandoen")
    private LocalDate creandoEn;

    @Column(name = "modificadopor")
    private String modificadoPor;

    @Column(name = "modificadoen")
    private LocalDate modificadoEn;

}