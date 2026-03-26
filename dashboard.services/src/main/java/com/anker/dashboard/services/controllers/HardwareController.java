package com.anker.dashboard.services.controllers;

import com.anker.dashboard.services.dtos.HardwareMetrics;
import com.anker.dashboard.services.services.HardwareMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hardware")
public class HardwareController {
    @Autowired  private HardwareMonitorService monitorService;

    @GetMapping
    public HardwareMetrics getHardwareStatus() {
        return monitorService.getMetrics();
    }
}
