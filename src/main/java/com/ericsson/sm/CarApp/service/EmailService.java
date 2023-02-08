package com.ericsson.sm.CarApp.service;

public interface EmailService {
    void send(String receiver, String subject, String text);
}
