package com.iot.home.applicaton.shared.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RecordDto implements Serializable {
    private Long id;
    private Double value;
    private LocalDateTime time;
    private String status;
    private DeviceDto DeviceDto;
}
