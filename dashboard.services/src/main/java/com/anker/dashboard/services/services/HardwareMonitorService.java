package com.anker.dashboard.services.services;

import com.anker.dashboard.services.dtos.HardwareMetrics;

public interface HardwareMonitorService {
    HardwareMetrics getMetrics();
}
