package com.smarthome.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthome.backend.entity.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    // Busca alertas pendientes de leer para un usuario específico [cite: 179]
    List<Alerta> findByIdUsuarioAndLeidaFalse(Long idUsuario);
}
