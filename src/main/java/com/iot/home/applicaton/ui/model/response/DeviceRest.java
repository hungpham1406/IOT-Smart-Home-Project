package com.iot.home.applicaton.ui.model.response;

import com.iot.home.applicaton.io.entity.RecordEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class DeviceRest {
    private String name;
    List<RecordRest> recordEntities;
}
