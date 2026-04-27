// src/app/models/models.ts

// Representa la tabla DISPOSITIVO de tu base de datos
export interface Dispositivo {
    idDispositivo: number;
    nombre: string;
    tipo: string;
    ubicacion: string;
    estado: string;
    idHub: number;
}

// Representa la tabla ALERTA de tu base de datos (por si la necesitas más adelante)
export interface Alerta {
    idAlerta: number;
    tipo: string;
    mensaje: string;
    fechaHora: string;
    leida: boolean;
    idDispositivo: number;
    idUsuario: number;
}