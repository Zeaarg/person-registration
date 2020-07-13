package com.example.personRegistration.services;

import com.example.personRegistration.exception.TimeoutException;
import com.example.personRegistration.model.Message;
import com.example.personRegistration.model.MessageId;

public interface MessagingService {

    <T> MessageId send(Message<T> msg);

    <T> Message<T> receive(MessageId messageId, Class<T> messageType) throws TimeoutException;
}
