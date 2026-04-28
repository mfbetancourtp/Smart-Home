import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  
  // Esta es la ruta de tu Spring Boot
  private apiUrl = 'http://localhost:8080/api/auth'; 

  login(credenciales: { correo: string, contrasena: string }): Observable<any> {
    // Hace una petición POST enviando el correo y la clave al backend
    return this.http.post(`${this.apiUrl}/login`, credenciales);
  }
  // En auth.service.ts
logout() {
  // Borramos el token o los datos del usuario del almacenamiento local
  localStorage.removeItem('user_token'); // O como lo hayas guardado
  sessionStorage.clear(); // Limpia todo por seguridad
}
}