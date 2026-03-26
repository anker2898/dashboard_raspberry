package com.anker.dashboard.services.services.impl;

import com.anker.dashboard.services.dtos.ContainerInfo;
import com.anker.dashboard.services.services.DockerManagerService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DockerManagerServiceImpl implements DockerManagerService {

    @Autowired private DockerClient dockerClient;

    public List<ContainerInfo> getContainers() {
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();

        return containers.stream().map(c -> {
            String rawName = c.getNames()[0];
            String cleanName = rawName.startsWith("/") ? rawName.substring(1) : rawName;

            return new ContainerInfo(
                    c.getId().substring(0, 12),
                    cleanName,
                    c.getImage(),
                    c.getState(),
                    c.getStatus()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    @Override
    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }
}
