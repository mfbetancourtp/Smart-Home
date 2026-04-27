package com.smarthome.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALERTA")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long idAlerta;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 255)
    private String mensaje;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Boolean leida = false;

    @Column(name = "id_dispositivo", nullable = false)
    private Long idDispositivo;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
}