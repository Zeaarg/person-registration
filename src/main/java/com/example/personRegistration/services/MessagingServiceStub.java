package com.example.personRegistration.services;

import com.example.personRegistration.exception.TimeoutException;
import com.example.personRegistration.model.Message;
import com.example.personRegistration.model.MessageId;
import com.example.personRegistration.model.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.personRegistration.Util.*;


@Service
public class MessagingServiceStub implements MessagingService {


    @Override
    public <T> MessageId send(Message<T> msg) {
        return new MessageId(UUID.randomUUID());
    }


    @Override
    public <T> Message<T> receive(MessageId messageId, Class<T> messageType) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Messaging service timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        Message<T> message = new Message<>();
        message.setMessage((T)new ResponseMessage(messageId, shouldApproved()));

        return message;
    }

}
