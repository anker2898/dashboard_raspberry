import { Component, signal } from '@angular/core';
import { Dashboard } from './components/dashboard/dashboard';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Dashboard], // Agregarlo a los imports
  template: `<app-dashboard></app-dashboard>`, // Limpiar el template por defecto y usar el nuestro})
})
export class App {
}
