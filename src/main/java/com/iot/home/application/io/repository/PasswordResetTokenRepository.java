package com.iot.home.application.io.repository;

import com.iot.home.application.io.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity,Long> {
    PasswordResetTokenEntity findPasswordResetTokenEntityByToken(String token);
}