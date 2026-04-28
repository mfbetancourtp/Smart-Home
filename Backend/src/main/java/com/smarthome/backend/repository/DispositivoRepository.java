package com.smarthome.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository; // o CrudRepository
import org.springframework.stereotype.Repository;

import com.smarthome.backend.entity.Dispositivo;

// ¡AQUÍ ESTÁ LA CLAVE! Debe decir <Dispositivo, Integer>
@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}