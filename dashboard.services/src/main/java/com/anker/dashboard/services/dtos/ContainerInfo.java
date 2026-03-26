package com.anker.dashboard.services.dtos;

public record ContainerInfo(
        String id,
        String name,
        String image,
        String state,  // ej. "running", "exited"
        String status  // ej. "Up 2 hours"
) {}