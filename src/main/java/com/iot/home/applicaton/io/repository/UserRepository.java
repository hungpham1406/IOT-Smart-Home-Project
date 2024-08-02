package com.iot.home.applicaton.io.repository;

import com.iot.home.applicaton.io.entity.UserEntity;
import com.iot.home.applicaton.shared.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findUserByEmailVerificationToken(String token);
}
