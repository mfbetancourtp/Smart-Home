import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DispositivoService } from '../../services/dispositivo.service';
import { Dispositivo } from '../../models/models';


@Component({
  selector: 'app-dispositivos',
  standalone: true,
  imports: [CommonModule], // Necesario para usar *ngIf y *ngFor en el HTML
  templateUrl: './dispositivos.component.html',
  styleUrls: ['./dispositivos.component.scss']
})
export class DispositivosComponent implements OnInit {
  // Variables para la vista
  dispositivos: Dispositivo[] = [];
  errorMessage: string = '';

  // Inyección de dependencias moderna en Angular
  private dispositivoService = inject(DispositivoService);

  ngOnInit(): void {
    this.cargarDispositivos();
  }

  // Método para consumir el endpoint GET
  cargarDispositivos(): void {
    this.dispositivoService.getDispositivos().subscribe({
      next: (data) => {
        this.dispositivos = data;
      },
      error: (err) => {
        this.errorMessage = err.message;
        console.error(err);
      }
    });
  }

  // Método para consumir el endpoint PUT
  cambiarEstado(id: number, nuevoEstado: string): void {
    this.dispositivoService.updateEstado(id, nuevoEstado).subscribe({
      next: (dispositivoActualizado) => {
        // Actualizamos el estado en la tabla local sin recargar la página
        const index = this.dispositivos.findIndex(d => d.idDispositivo === id);
        if (index !== -1) {
          this.dispositivos[index] = dispositivoActualizado;
        }
      },
      error: (err) => {
        alert('Hubo un error al cambiar el estado: ' + err.message);
      }
    });
  }
}