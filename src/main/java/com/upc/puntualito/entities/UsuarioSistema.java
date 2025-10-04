package com.upc.puntualito.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "usuariosistema")
public class UsuarioSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(name = "nombredeusuario", nullable = false, length = 50)
    private String nombreDeUsuario;

    @Column(name = "clavehash", nullable = false)
    private Integer claveHash;

    @Column(name = "rol", nullable = false, length = 100)
    private String rol;

    @Column(name = "ultimoacceso", nullable = false)
    private Instant ultimoAcceso;

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