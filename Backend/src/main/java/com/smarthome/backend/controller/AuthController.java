package com.smarthome.backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.backend.entity.Usuario;
import com.smarthome.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(originPatterns = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String contrasena = credenciales.get("contrasena");

        // 1. Buscamos el usuario en PostgreSQL usando el correo
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);

        // Si el usuario existe en la BD
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            // 2. Comparamos la contraseña de la BD con la que escribió en Angular
            if (usuario.getContrasenaHash().equals(contrasena) && usuario.getActivo()) {
                
                Map<String, Object> respuestaExito = new HashMap<>();
                respuestaExito.put("mensaje", "Login exitoso");
                respuestaExito.put("token", "fake-jwt-token-123");
                respuestaExito.put("nombre_usuario", usuario.getNombre()); // Le devolvemos su nombre
                
                return ResponseEntity.ok().body(respuestaExito);
            }
        }
        
        // Si no existe o la clave está mal
        Map<String, String> respuestaError = new HashMap<>();
        respuestaError.put("error", "Credenciales inválidas o usuario inactivo");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuestaError);
    }
}