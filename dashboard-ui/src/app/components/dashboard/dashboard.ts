import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { HardwareService } from '../../services/hardware';
import { ContainerList } from '../container-list/container-list';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ContainerList],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit, OnDestroy {
  // 1. Inyectamos nuestro servicio reactivo
  public hardwareService = inject(HardwareService);

  // Guardamos la referencia del temporizador para poder destruirlo después
  private pollingInterval: any;

  ngOnInit(): void {
    // 2. Hacemos la primera lectura de los "sensores" inmediatamente
    this.hardwareService.fetchMetrics();

    // 3. Configuramos el ciclo para que actualice cada 2 segundos (2000ms)
    this.pollingInterval = setInterval(() => {
      this.hardwareService.fetchMetrics();
    }, 2000);
  }

  ngOnDestroy(): void {
    // 4. Buenas prácticas 2026: Siempre limpiar los intervalos si el componente se destruye
    // para evitar fugas de memoria (memory leaks).
    if (this.pollingInterval) {
      clearInterval(this.pollingInterval);
    }
  }
}