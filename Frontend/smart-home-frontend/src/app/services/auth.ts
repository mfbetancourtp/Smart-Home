import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

// 1. Importa tu archivo environment (asegúrate de que la ruta coincida con la estructura de tus carpetas)

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  
  // 2. Reemplaza el localhost usando la variable que definimos en el environment
  private apiUrl = `${environment.backendUrl}/api/auth`; 

  login(credenciales: { correo: string, contrasena: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credenciales);
  }

  logout() {
    localStorage.removeItem('user_token'); // O como lo hayas guardado
    sessionStorage.clear(); // Limpia todo por seguridad
  }
}