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
  cargando: boolean = false; // <-- 1. Nueva variable de estado

  private router = inject(Router);
  private authService = inject(AuthService);

  iniciarSesion() {
    this.cargando = true; // <-- 2. Activamos el loader
    this.mensajeError = ''; // Limpiamos errores previos

    const credenciales = { correo: this.correo, contrasena: this.contrasena };

    this.authService.login(credenciales).subscribe({
      next: (respuesta: any) => {
        this.cargando = false; // <-- 3. Apagamos el loader al terminar
        console.log('Login exitoso desde la base de datos:', respuesta);
        this.router.navigate(['/dashboard']); 
      },
      error: (err: any) => {
        this.cargando = false; // <-- 4. También lo apagamos si hay error
        console.error('Error de login:', err);
        this.mensajeError = 'Correo o contraseña incorrectos. Intenta de nuevo.';
      }
    });
  }
}