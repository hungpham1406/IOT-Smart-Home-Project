package com.iot.home.application.io.repository;

import com.iot.home.application.io.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity,Long> {
    @Query("SELECT r FROM RecordEntity r WHERE r.deviceEntity.id = :deviceId AND r.time >= :startOfDay")
    List<RecordEntity> getRecordByDay(@Param("deviceId") Long deviceId, @Param("startOfDay") LocalDateTime startOfDay);

    @Query("SELECT r FROM RecordEntity r WHERE r.deviceEntity.id = :deviceId AND r.time >= :startOfWeek")
    List<RecordEntity> getRecordByWeek(@Param("deviceId") Long deviceId, @Param("startOfWeek") LocalDateTime startOfWeek);

    @Query("SELECT r FROM RecordEntity r WHERE r.deviceEntity.id = :deviceId AND r.time >= :startOfMonth")
    List<RecordEntity> getRecordByMonth(@Param("deviceId") Long deviceId, @Param("startOfMonth") LocalDateTime startOfMonth);

    @Query("SELECT r FROM RecordEntity r WHERE r.deviceEntity.id = :deviceId ORDER BY r.time DESC")
    List<RecordEntity> getLatestRecord(@Param("deviceId") Long deviceId);


}
