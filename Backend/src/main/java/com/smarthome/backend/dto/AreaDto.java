package com.smarthome.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {
    private String id;
    private String sigla;
    private String nombreOficinaProductora;
    private String objectStore;
    
    // --- NUEVOS CAMPOS AÑADIDOS PARA EL MOCKUP ---
    private String codigoOficinaProductora; 
    private String fileSystemName;
    // ---------------------------------------------
    
    private String etiqueta; // Lo dejamos por si lo usas en otra parte
}