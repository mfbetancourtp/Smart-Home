import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dispositivo } from '../models/models';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class DispositivoService {
  
  // URL de tu backend Spring Boot (ej: http://localhost:8080/api/dispositivos)
  private apiUrl = `${environment.backendUrl}/api/dispositivos`;

  // Inyectamos el HttpClient para poder hacer las peticiones
  constructor(private http: HttpClient) { }

  // ¡AQUÍ ESTÁ EL MÉTODO QUE TE FALTABA!
  // Trae la lista de dispositivos desde el backend
  getDispositivos(): Observable<Dispositivo[]> {
    return this.http.get<Dispositivo[]>(this.apiUrl);
  }

  // Método para encender/apagar o cambiar el estado de un dispositivo
  updateEstado(id: number, estado: string): Observable<Dispositivo> {
    return this.http.put<Dispositivo>(`${this.apiUrl}/${id}/estado`, { estado });
  }

  cambiarEstado(id: number, nuevoEstado: string): Observable<any> {
    const body = { estado: nuevoEstado };
    return this.http.put(`${this.apiUrl}/${id}/estado`, body);
  }

  crearDispositivo(dispositivo: any): Observable<any> {
    return this.http.post(this.apiUrl, dispositivo);
  }

  eliminarDispositivo(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}