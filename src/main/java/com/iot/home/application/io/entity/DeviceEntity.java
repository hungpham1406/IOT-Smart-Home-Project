package com.iot.home.application.io.entity;

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
    @Column(nullable = false,length = 20)
    private String name;
    private String feedKey;
    @OneToMany(mappedBy = "deviceEntity", cascade = CascadeType.ALL)
    List<RecordEntity> recordEntities;
}
