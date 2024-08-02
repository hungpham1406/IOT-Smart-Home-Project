package com.iot.home.applicaton.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DeviceRest {
    private Long id;
    private String name;
    private String period;
    List<RecordRest> data;
}
