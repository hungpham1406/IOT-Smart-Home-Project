package com.iot.home.applicaton.service;

import com.iot.home.applicaton.shared.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> getDevices();
    DeviceDto addDevices(String name,String feedKey);
    DeviceDto getDeviceRecords(String id,String period);
}
