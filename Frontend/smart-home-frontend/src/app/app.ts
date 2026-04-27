import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DispositivosComponent } from './components/dispositivos/dispositivos.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, DispositivosComponent],
  templateUrl: './app.html', 
  styleUrl: './app.scss'
})
export class App { 
  title = 'smart-home-frontend';
}