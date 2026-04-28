package com.smarthome.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthome.backend.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Spring Boot escribirá el "SELECT * FROM USUARIO WHERE correo = ?" automáticamente por nosotros
    Optional<Usuario> findByCorreo(String correo);
}