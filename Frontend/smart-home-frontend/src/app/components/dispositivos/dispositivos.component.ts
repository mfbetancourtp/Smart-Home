import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; 
import { DispositivoService } from '../../services/dispositivo.service';
import { Dispositivo } from '../../models/models';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-dispositivos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dispositivos.component.html',
  styleUrls: ['./dispositivos.component.scss']
})
export class DispositivosComponent implements OnInit {
  dispositivos: Dispositivo[] = [];
  errorMessage: string = '';
  successMessage: string = '';

  private dispositivoService = inject(DispositivoService);
  private authService = inject(AuthService);
  private router = inject(Router);
  private cdr = inject(ChangeDetectorRef); 

  nuevoDispositivo = {
    nombre: '',
    tipo: 'Iluminación',
    ubicacion: ''
  };

  ngOnInit(): void {
    this.cargarDispositivos();
  }

  cargarDispositivos(): void {
    this.dispositivoService.getDispositivos().subscribe({
      next: (data) => {
        this.dispositivos = data;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        this.errorMessage = 'No se pudieron cargar los dispositivos.';
        console.error(err);
      }
    });
  }

  cambiarEstado(dispositivo: any): void {
    const nuevoEstado = dispositivo.estado === 'Activo' ? 'Inactivo' : 'Activo';
    this.dispositivoService.cambiarEstado(dispositivo.idDispositivo, nuevoEstado).subscribe({
      next: () => {
        dispositivo.estado = nuevoEstado;
        this.successMessage = `Estado de ${dispositivo.nombre} actualizado a ${nuevoEstado}`;
        setTimeout(() => this.successMessage = '', 2000);
        this.cdr.detectChanges();
      },
      error: (err) => alert('Error al cambiar estado')
    });
  }

  agregarDispositivo(): void {
    if (!this.nuevoDispositivo.nombre || !this.nuevoDispositivo.ubicacion) {
      alert('Por favor, completa todos los campos.');
      return;
    }

    this.dispositivoService.crearDispositivo(this.nuevoDispositivo).subscribe({
      next: (creado) => {
        // Actualización inmediata del array
        this.dispositivos.push(creado);
        this.nuevoDispositivo = { nombre: '', tipo: 'Iluminación', ubicacion: '' };
        
        this.successMessage = '✅ ¡Dispositivo añadido correctamente!';
        setTimeout(() => this.successMessage = '', 3000);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        alert('Error al crear el dispositivo');
      }
    });
  }

  eliminarDispositivo(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar este dispositivo?')) {
      this.dispositivoService.eliminarDispositivo(id).subscribe({
        next: () => {
          // Actualización inmediata quitando el registro del array
          this.dispositivos = this.dispositivos.filter(d => d.idDispositivo !== id);
          this.successMessage = '🗑️ Dispositivo eliminado de la red';
          setTimeout(() => this.successMessage = '', 3000);
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error(err);
          alert('No se pudo eliminar el dispositivo');
        }
      });
    }
  }

  cerrarSesion() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}