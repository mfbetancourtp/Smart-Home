package com.smarthome.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DISPOSITIVO")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Long idDispositivo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 100)
    private String ubicacion;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(name = "id_hub", nullable = false)
    private Long idHub;
}