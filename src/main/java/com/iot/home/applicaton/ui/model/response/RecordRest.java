package com.iot.home.applicaton.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RecordRest {
    private Timestamp timestamp;
    private Long id;
}
