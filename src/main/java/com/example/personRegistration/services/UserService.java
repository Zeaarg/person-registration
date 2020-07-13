package com.example.personRegistration.services;

import com.example.personRegistration.model.Message;
import com.example.personRegistration.model.ResponseMessage;
import com.example.personRegistration.model.User;

public interface UserService {

    User saveUser(User user);

    Message checkUser(User user);

    boolean sendEmail(User user, Message<ResponseMessage> message);
}
