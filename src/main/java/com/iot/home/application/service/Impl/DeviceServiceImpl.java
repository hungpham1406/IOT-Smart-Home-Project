package com.iot.home.application.service.Impl;

import com.iot.home.application.io.entity.DeviceEntity;
import com.iot.home.application.io.entity.RecordEntity;
import com.iot.home.application.io.repository.DeviceRepository;
import com.iot.home.application.io.repository.RecordRepository;
import com.iot.home.application.service.DeviceService;
import com.iot.home.application.shared.Utils;
import com.iot.home.application.shared.dto.DeviceDto;
import com.iot.home.application.shared.dto.RecordDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RecordRepository recordRepository;

    @Override
    public List<DeviceDto> getDevices() {
        List<DeviceEntity> deviceEntities= deviceRepository.findAll();
        java.lang.reflect.Type listType = new TypeToken<List<DeviceDto>>() {}.getType();
        ModelMapper modelMapper= new ModelMapper();
        List<DeviceDto> returnValue= modelMapper.map(deviceEntities,listType);
        return returnValue;
    }

    @Override
    public DeviceDto addDevices(String name,String feedKey) {
        DeviceEntity checkExist= deviceRepository.findByName(name);
        if(checkExist!=null) throw new RuntimeException("Devices already exits");
        String deviceId= Utils.generateDeviceId(30);
        DeviceEntity deviceEntity= new DeviceEntity();
        deviceEntity.setRecordEntities(null);
        deviceEntity.setName(name);
        deviceEntity.setFeedKey(feedKey);
        deviceRepository.save(deviceEntity);
        ModelMapper mapper= new ModelMapper();
        DeviceDto returnValue=mapper.map(deviceEntity,DeviceDto.class);
        return returnValue;
    }

    public DeviceDto getDeviceRecords(String id, String period) {
        DeviceDto returnValue= new DeviceDto();
        if ((deviceRepository.findById(Long.valueOf(id))).isEmpty()) throw new RuntimeException("Device not found");
        DeviceEntity deviceEntity =(deviceRepository.findById(Long.valueOf(id))).get();
        returnValue.setId(deviceEntity.getId());
        returnValue.setName(deviceEntity.getName());
        List<RecordEntity> recordEntities;
        Long deviceId = deviceEntity.getId();
        LocalDateTime now = LocalDateTime.now();

        switch (period) {
            case "day" -> {
                LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
                recordEntities = recordRepository.getRecordByDay(deviceId, startOfDay);
            }
            case "week" -> {
                LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay();
                recordEntities = recordRepository.getRecordByWeek(deviceId, startOfWeek);
            }
            case "month" -> {
                LocalDateTime startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atStartOfDay();
                recordEntities = recordRepository.getRecordByMonth(deviceId, startOfMonth);
            }
            case "latest" -> {
                recordEntities = recordRepository.getLatestRecord(deviceId);
            }
            default -> throw new IllegalArgumentException("Invalid period specified");
        }
        java.lang.reflect.Type listType = new TypeToken<List<RecordEntity>>() {}.getType();
        ModelMapper modelMapper= new ModelMapper();
        List<RecordDto> recordDtos=modelMapper.map(recordEntities,listType);
        returnValue.setRecordDtos(recordDtos);

        return  returnValue;

    }
}
