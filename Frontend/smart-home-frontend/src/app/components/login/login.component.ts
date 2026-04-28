import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  correo: string = '';
  contrasena: string = '';
  mensajeError: string = '';

  private router = inject(Router);
  private authService = inject(AuthService); // <-- Inyectamos el servicio

  iniciarSesion() {
    const credenciales = { correo: this.correo, contrasena: this.contrasena };

    // Llamamos al backend real
    this.authService.login(credenciales).subscribe({
      next: (respuesta: any) => {
        // Si Java responde 200 OK (Credenciales correctas)
        console.log('Login exitoso desde la base de datos:', respuesta);
        this.mensajeError = '';
        this.router.navigate(['/dashboard']); 
      },
      error: (err: any) => {
        // Si Java responde 401 (Error de credenciales)
        console.error('Error de login:', err);
        this.mensajeError = 'Correo o contraseña incorrectos. Intenta de nuevo.';
      }
    });
  }
}