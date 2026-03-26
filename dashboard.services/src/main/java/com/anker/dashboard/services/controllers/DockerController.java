package com.anker.dashboard.services.controllers;

import com.anker.dashboard.services.dtos.ContainerInfo;
import com.anker.dashboard.services.services.DockerManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docker")
public class DockerController {

    @Autowired private DockerManagerService dockerService;

    @GetMapping("/containers")
    public List<ContainerInfo> listContainers() {
        return dockerService.getContainers();
    }

    @PostMapping("containers/{id}/start")
    public void startContainer(@PathVariable String id) {
        dockerService.startContainer(id);
    }

    @PostMapping("containers/{id}/stop")
    public void stopContainer(@PathVariable String id) {
        dockerService.stopContainer(id);
    }
}