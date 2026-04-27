package com.smarthome.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smarthome.backend.entity.Alerta;
import com.smarthome.backend.repository.AlertaRepository;
import com.smarthome.backend.service.AlertaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertaServiceImplementation implements AlertaService {

    private final AlertaRepository alertaRepo;

    @Override
    public List<Alerta> obtenerAlertasUsuario(Long idUsuario) {
        return alertaRepo.findByIdUsuarioAndLeidaFalse(idUsuario);
    }

    @Override
    public void marcarComoLeida(Long idAlerta) {
        alertaRepo.findById(idAlerta).ifPresent(alerta -> {
            alerta.setLeida(true);
            alertaRepo.save(alerta);
        });
    }

    @Override
    public Alerta generarAlerta(Alerta alerta) {
        alerta.setFechaHora(LocalDateTime.now()); 
        return alertaRepo.save(alerta);
    }
}