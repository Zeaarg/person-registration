package com.example.personRegistration.services;

import com.example.personRegistration.exception.TimeoutException;
import com.example.personRegistration.model.EmailContent;

public interface SendMailer {

    void sendMail (String address, EmailContent messageBody) throws TimeoutException;
}
