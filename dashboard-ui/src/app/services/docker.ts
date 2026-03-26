import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { ContainerInfo } from '../models/container-info.model';

@Injectable({
  providedIn: 'root',
})
export class Docker {
  private http = inject(HttpClient);

  // Inicializamos el Signal como un arreglo vacío
  public containers = signal<ContainerInfo[]>([]);

  private apiUrl = 'http://localhost:8080/api/docker/containers';

  public fetchContainers(): void {
    this.http.get<ContainerInfo[]>(this.apiUrl).subscribe({
      next: (data) => this.containers.set(data),
      error: (err) => console.error('Error leyendo Docker daemon:', err)
    });
  }

  public startContainer(id: string): void {
    this.http.post(`${this.apiUrl}/${id}/start`, {}).subscribe({
      next: () => this.fetchContainers(), // Refrescamos la lista al terminar
      error: (err) => console.error('Error arrancando sistema:', err)
    });
  }

  public stopContainer(id: string): void {
    this.http.post(`${this.apiUrl}/${id}/stop`, {}).subscribe({
      next: () => this.fetchContainers(),
      error: (err) => console.error('Error deteniendo sistema:', err)
    });
  }
}
