package com.iot.home.application.io.repository;

import com.iot.home.application.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findUserByEmailVerificationToken(String token);
}
