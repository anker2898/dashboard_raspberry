package com.anker.dashboard.services.services;

import com.anker.dashboard.services.dtos.ContainerInfo;

import java.util.List;

public interface DockerManagerService {
    List<ContainerInfo> getContainers();
    void startContainer(String containerId);
    void stopContainer(String containerId);
}
