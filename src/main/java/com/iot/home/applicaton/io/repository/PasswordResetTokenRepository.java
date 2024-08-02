package com.iot.home.applicaton.io.repository;

import com.iot.home.applicaton.io.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity,Long> {
    PasswordResetTokenEntity findPasswordResetTokenEntityByToken(String token);
}