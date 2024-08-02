package com.iot.home.applicaton.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class DeviceEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,length = 30)
    private String deviceId;
    @Column(nullable = false,length = 20)
    private String name;
    @OneToMany(mappedBy = "deviceEntity", cascade = CascadeType.ALL)
    List<RecordEntity> recordEntities;
}
