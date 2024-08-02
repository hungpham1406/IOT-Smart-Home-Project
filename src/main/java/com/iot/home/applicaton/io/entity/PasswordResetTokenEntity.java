package com.iot.home.applicaton.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetTokenEntity implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    private String token;
    @OneToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;
}
