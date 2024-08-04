package com.iot.home.applicaton.shared.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
