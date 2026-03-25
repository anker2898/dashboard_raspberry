package com.anker.dashboard.services.services.impl;

import com.anker.dashboard.services.dtos.HardwareMetrics;
import com.anker.dashboard.services.services.HardwareMonitorService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

@Service
public class HardwareMonitorServiceImpl implements HardwareMonitorService {

    private final HardwareAbstractionLayer hal;
    private long[] previousTicks;

    public HardwareMonitorServiceImpl() {
        SystemInfo systemInfo = new SystemInfo();
        this.hal = systemInfo.getHardware();
        this.previousTicks = hal.getProcessor().getSystemCpuLoadTicks();
    }

    @Override
    public HardwareMetrics getMetrics() {
        CentralProcessor processor = hal.getProcessor();
        Sensors sensors = hal.getSensors();

        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(previousTicks) * 100;
        previousTicks = processor.getSystemCpuLoadTicks();

        double temperature = sensors.getCpuTemperature();

        return new HardwareMetrics(
                Math.round(cpuLoad * 10.0) / 10.0,
                Math.round(temperature * 10.0) / 10.0
        );
    }
}
