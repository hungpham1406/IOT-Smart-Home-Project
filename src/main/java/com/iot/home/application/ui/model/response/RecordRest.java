package com.iot.home.application.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RecordRest {
    private Double value;
    private LocalDateTime time;
    private String status;
}
