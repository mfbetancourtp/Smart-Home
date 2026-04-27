package com.smarthome.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthome.backend.entity.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    // Busca dispositivos por ubicación (Piso 1, Piso 2, etc.) [cite: 171]
    List<Dispositivo> findByUbicacion(String ubicacion);
}