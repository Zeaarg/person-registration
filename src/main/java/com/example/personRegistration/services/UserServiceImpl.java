package com.example.personRegistration.services;

import com.example.personRegistration.Scheduler;
import com.example.personRegistration.dao.UserDao;
import com.example.personRegistration.exception.TimeoutException;
import com.example.personRegistration.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private SendMailer sendMailer;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public User saveUser(User user) {
        userDao.save(user);
        taskExecutor.execute(() -> {
            Message m = checkUser(user);
            if (m != null)
                sendEmail(user, m);
        });
        return user;
    }

    @Override
    public Message checkUser(User user) {
        try {
            Message<User> msg = new Message<>(user);
            MessageId id = messagingService.send(msg);
            Message<ResponseMessage> message = messagingService.receive(id, ResponseMessage.class);
            return message;
        } catch (TimeoutException e) {
            if (!Scheduler.notCheckedUsers.contains(user))
                Scheduler.notCheckedUsers.add(user);
            logger.info(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean sendEmail(User user, Message<ResponseMessage> message) {
        try {
            if (message != null && message.getMessage() != null){
                EmailContent emailContent = new EmailContent();
                if (message.getMessage().isApproved())
                    emailContent.setContent("Заявка одобрена");
                else
                    emailContent.setContent("Заявка отклонена");
                sendMailer.sendMail(user.getEmail(), emailContent);
            }
        } catch (TimeoutException e) {
            Object[] obj = new Object[]{user, message};
            if (!Scheduler.notSendEmail.contains(obj))
                Scheduler.notSendEmail.add(obj);
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }
}
