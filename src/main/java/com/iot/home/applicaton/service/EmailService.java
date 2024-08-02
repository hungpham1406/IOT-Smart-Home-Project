package com.iot.home.applicaton.service;

public interface EmailService {
    void sendVerificationEmail(String to, String subject, String text);
}
