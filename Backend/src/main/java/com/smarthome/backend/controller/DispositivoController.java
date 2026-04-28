package com.smarthome.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.backend.entity.Dispositivo;
import com.smarthome.backend.repository.DispositivoRepository;

@RestController
@RequestMapping("/api/dispositivos")
@CrossOrigin(originPatterns = "*")
public class DispositivoController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    // Ruta para OBTENER todos los dispositivos
    @GetMapping
    public List<Dispositivo> obtenerDispositivos() {
        return dispositivoRepository.findAll();
    }

    // Ruta para ACTUALIZAR el estado de un dispositivo específico
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        
        // Como el Repositorio ya es Integer, y el 'id' que entra es Integer, esto ya no dará error
        Optional<Dispositivo> opt = dispositivoRepository.findById(id); 
        
        if (opt.isPresent()) {
            Dispositivo dispositivo = opt.get();
            dispositivo.setEstado(body.get("estado"));
            dispositivoRepository.save(dispositivo);
            return ResponseEntity.ok().body(dispositivo);
        }
        return ResponseEntity.notFound().build();
    }
    
 // Agrégalo dentro de tu clase DispositivoController
    @PostMapping
    public ResponseEntity<?> crearDispositivo(@RequestBody Dispositivo nuevoDispositivo) {
        // Le asignamos valores por defecto para evitar errores
        nuevoDispositivo.setIdHub(1); // Todo va al Hub principal por ahora
        nuevoDispositivo.setEstado("Inactivo"); // Entra apagado por seguridad
        
        // Lo guardamos en la base de datos (puedes usar repo.save o el service.guardar)
        Dispositivo creado = dispositivoRepository.save(nuevoDispositivo); 
        
        return ResponseEntity.ok(creado);
    }
 // Añade esto a DispositivoController.java
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDispositivo(@PathVariable Integer id) {
        return dispositivoRepository.findById(id).map(d -> {
            dispositivoRepository.delete(d);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    
}