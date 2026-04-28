package com.smarthome.backend.service;

import java.util.List;
import com.smarthome.backend.entity.Dispositivo;

public interface DispositivoService {
    
    List<Dispositivo> listarTodos();
    
    // AQUÍ ESTABA EL ERROR: Cambiamos Long por Integer
    Dispositivo obtenerPorId(Integer id); 
    
    Dispositivo guardar(Dispositivo dispositivo);
    
    // AQUÍ TAMBIÉN: Cambiamos Long por Integer
    Dispositivo actualizarEstado(Integer id, String nuevoEstado); 
    
    // Y AQUÍ: Cambiamos Long por Integer
    void eliminar(Integer id); 
}