package com.iot.home.applicaton.service.Impl;

import com.iot.home.applicaton.io.entity.DeviceEntity;
import com.iot.home.applicaton.io.repository.DeviceRepository;
import com.iot.home.applicaton.service.DeviceService;
import com.iot.home.applicaton.shared.Utils;
import com.iot.home.applicaton.shared.dto.DeviceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public List<DeviceDto> getDevices() {
        return List.of();
    }

    @Override
    public DeviceDto addDevices(String name) {
        DeviceEntity checkExist= deviceRepository.findByName(name);
        if(checkExist!=null) throw new RuntimeException("Devices already exits");
        String deviceId= Utils.generateDeviceId(30);
        DeviceEntity deviceEntity= new DeviceEntity();
        deviceEntity.setDeviceId(deviceId);
        deviceEntity.setRecordEntities(null);
        deviceEntity.setName(name);
        deviceRepository.save(deviceEntity);
        ModelMapper mapper= new ModelMapper();
        DeviceDto returnValue=mapper.map(deviceEntity,DeviceDto.class);
        return returnValue;
    }
}
