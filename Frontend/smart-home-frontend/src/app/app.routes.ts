import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DispositivosComponent } from './components/dispositivos/dispositivos.component';

export const routes: Routes = [
  // Si alguien entra a la raíz, lo enviamos directo al login
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  // Nuestras dos rutas principales
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DispositivosComponent }

];