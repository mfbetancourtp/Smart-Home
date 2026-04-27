package com.smarthome.backend.service;

import java.util.List;

import com.smarthome.backend.entity.Alerta;

public interface AlertaService {
    List<Alerta> obtenerAlertasUsuario(Long idUsuario);
    void marcarComoLeida(Long idAlerta);
    Alerta generarAlerta(Alerta alerta);
}