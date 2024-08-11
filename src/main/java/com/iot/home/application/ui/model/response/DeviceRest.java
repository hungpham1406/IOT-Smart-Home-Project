package com.iot.home.application.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DeviceRest {
    private String name;
    List<RecordRest> recordEntities;
}
