package com.example.personRegistration.services;

import com.example.personRegistration.dao.UserDao;
import com.example.personRegistration.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private SendMailer sendMailer;

    @Mock
    private MessagingService messagingService;

    @Mock
    private UserDao userDao;

    @Mock
    private TaskExecutor taskExecutor;

    @Test
    public void saveUserTest() throws Exception{
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setMiddleName("middle");
        user.setId(1L);
        user.setEmail("example@gmail.com");
        user.setLogin("login");
        user.setPassword("password");
        when(userDao.save(any(User.class))).thenReturn(user);
        doNothing().when(taskExecutor).execute(any(Runnable.class));
        User savedUser = userServiceImpl.saveUser(user);
        Assert.assertEquals(1, (long)savedUser.getId());
        Assert.assertEquals("example@gmail.com", user.getEmail());
    }

    @Test
    public void checkUserTest() throws Exception{
        User user = new User();
        MessageId messageId = new MessageId(UUID.randomUUID());
        Message<ResponseMessage> message = new Message<>();
        message.setMessageId(messageId);
        when(messagingService.send(any(Message.class))).thenReturn(messageId);
        when(messagingService.receive(any(MessageId.class), any(Class.class))).thenReturn(message);
        Message expectedMessage = userServiceImpl.checkUser(user);
        Assert.assertEquals(messageId.getUuid(), expectedMessage.getMessageId().getUuid());
    }

    @Test
    public void sendEmailTest() throws Exception {
        User user = new User();
        user.setEmail("example@gmail.com");
        ResponseMessage responseMessage = new ResponseMessage();
        Message<ResponseMessage> message = new Message<>();
        message.setMessage(responseMessage);
        doNothing().when(sendMailer).sendMail(anyString(), any(EmailContent.class));
        boolean expected = userServiceImpl.sendEmail(user, message);
        Assert.assertEquals(true, expected);
    }
}
