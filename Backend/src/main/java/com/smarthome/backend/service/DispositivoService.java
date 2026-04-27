package com.smarthome.backend.service;

import java.util.List;

import com.smarthome.backend.entity.Dispositivo;

public interface DispositivoService {
    List<Dispositivo> listarTodos();
    Dispositivo obtenerPorId(Long id);
    Dispositivo guardar(Dispositivo dispositivo);
    Dispositivo actualizarEstado(Long id, String nuevoEstado);
    void eliminar(Long id);
}