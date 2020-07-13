package com.example.personRegistration.services;

import com.example.personRegistration.exception.TimeoutException;
import com.example.personRegistration.model.EmailContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.personRegistration.Util.*;

@Service
public class SendMailerStub implements SendMailer {

    private static Logger logger = LoggerFactory.getLogger(SendMailerStub.class);

    @Override
    public void sendMail(String address, EmailContent messageBody) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Send mailer timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }
        logger.info("Message sent to {}, body {}.", address, messageBody.getContent());
    }
}
