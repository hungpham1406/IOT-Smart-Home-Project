package com.iot.home.application.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
