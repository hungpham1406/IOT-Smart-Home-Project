package com.iot.home.application.service;

public interface EmailService {
    void sendVerificationEmail(String to, String subject, String text);
}
