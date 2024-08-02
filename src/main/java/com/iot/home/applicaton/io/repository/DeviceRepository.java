package com.iot.home.applicaton.io.repository;

import com.iot.home.applicaton.io.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity,Long> {
    DeviceEntity findByName(String name);
}
