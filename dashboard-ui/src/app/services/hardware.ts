import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HardwareMetrics } from '../models/hardware-metrics.model';

@Injectable({
  providedIn: 'root',
})
export class HardwareService {
  // 1. Inyección de dependencias moderna (sin usar el constructor)
  private http = inject(HttpClient);

  // 2. Definimos nuestro Signal con valores iniciales en cero
  // Un Signal es como un contenedor inteligente que avisa cuando su valor cambia
  public metrics = signal<HardwareMetrics>({ cpuPercentage: 0, temperatureCelsius: 0 });

  private apiUrl = 'http://localhost:8080/api/hardware';

  // 3. Método para obtener los datos y actualizar el Signal
  public fetchMetrics(): void {
    this.http.get<HardwareMetrics>(this.apiUrl).subscribe({
      next: (data) => {
        // Usamos .set() para actualizar el valor del Signal con el nuevo JSON
        this.metrics.set(data);
      },
      error: (err) => console.error('Error obteniendo telemetría:', err)
    });
  }
}