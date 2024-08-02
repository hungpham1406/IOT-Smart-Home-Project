package com.iot.home.applicaton.io.repository;

import com.iot.home.applicaton.io.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity,Long> {
}
