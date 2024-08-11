package com.iot.home.application.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DeviceDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    List<RecordDto> recordDtos;

}
