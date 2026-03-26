import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { Docker } from '../../services/docker';

@Component({
  selector: 'app-container-list',
  standalone: true,
  templateUrl: './container-list.html',
  styleUrl: './container-list.css',
})
export class ContainerList implements OnInit, OnDestroy {
  public dockerService = inject(Docker);
  private pollingInterval: any;

  ngOnInit(): void {
    this.dockerService.fetchContainers();
    // Refresco cada 5 segundos
    this.pollingInterval = setInterval(() => {
      this.dockerService.fetchContainers();
    }, 5000);
  }

  ngOnDestroy(): void {
    if (this.pollingInterval) {
      clearInterval(this.pollingInterval);
    }
  }
}
