package com.smarthome.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.backend.entity.Dispositivo;
import com.smarthome.backend.exception.ResourceNotFoundException;
import com.smarthome.backend.repository.DispositivoRepository;

@RestController
@RequestMapping("/api/dispositivos")
@CrossOrigin(originPatterns = "*") // <-- Aquí está la configuración de CORS solicitada
public class DispositivoController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @GetMapping
    public List<Dispositivo> getAllDispositivos() {
        return dispositivoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> getDispositivoById(@PathVariable Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dispositivo no encontrado con el id: " + id));
        return ResponseEntity.ok(dispositivo);
    }

    @PostMapping
    public Dispositivo createDispositivo(@RequestBody Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Dispositivo> updateEstadoDispositivo(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dispositivo no encontrado con el id: " + id));
        
        dispositivo.setEstado(body.get("estado"));
        Dispositivo actualizado = dispositivoRepository.save(dispositivo);
        return ResponseEntity.ok(actualizado);
    }
}