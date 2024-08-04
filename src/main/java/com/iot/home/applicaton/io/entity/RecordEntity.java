package com.iot.home.applicaton.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "records")
public class RecordEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Double value;
    private LocalDateTime time;
    private String status;
    @ManyToOne
    @JoinColumn(name="devices_id")
    DeviceEntity deviceEntity;

}
