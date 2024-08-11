package com.iot.home.application.io.repository;

import com.iot.home.application.io.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity,Long> {
    DeviceEntity findByName(String name);
    List<DeviceEntity> findAll();

    Optional<DeviceEntity> findById(Long id);
}
