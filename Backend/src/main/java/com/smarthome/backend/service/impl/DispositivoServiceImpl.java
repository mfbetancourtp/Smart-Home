package com.smarthome.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smarthome.backend.entity.Dispositivo;
import com.smarthome.backend.exception.ResourceNotFoundException;
import com.smarthome.backend.repository.DispositivoRepository;
import com.smarthome.backend.service.DispositivoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispositivoServiceImpl implements DispositivoService {

    private final DispositivoRepository repo;

    @Override
    @Transactional(readOnly = true)
    public List<Dispositivo> listarTodos() {
        return repo.findAll();
    }

    // CAMBIO AQUÍ: Integer en lugar de Long
    @Override
    @Transactional(readOnly = true)
    public Dispositivo obtenerPorId(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dispositivo no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Dispositivo guardar(Dispositivo dispositivo) {
        // Lógica de negocio: Validar que el tipo de dispositivo sea válido
        return repo.save(dispositivo);
    }

    // CAMBIO AQUÍ: Integer en lugar de Long
    @Override
    @Transactional
    public Dispositivo actualizarEstado(Integer id, String nuevoEstado) {
        Dispositivo d = obtenerPorId(id);
        d.setEstado(nuevoEstado); 
        return repo.save(d);
    }

    // CAMBIO AQUÍ: Integer en lugar de Long
    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: ID no existe");
        }
        repo.deleteById(id);
    }
}