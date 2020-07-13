package com.example.personRegistration;

import com.example.personRegistration.model.Message;
import com.example.personRegistration.model.ResponseMessage;
import com.example.personRegistration.model.User;
import com.example.personRegistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Scheduler {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskExecutor taskExecutor;

    public static CopyOnWriteArrayList<User> notCheckedUsers = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Object[]> notSendEmail = new CopyOnWriteArrayList<>();

    @Scheduled(fixedDelay = 20000)
    public void schedulerNotCheckedUsers(){
        notCheckedUsers.forEach((user) -> {
            taskExecutor.execute(() -> {
                Message message = userService.checkUser(user);
                if (message == null)
                    return;
                notCheckedUsers.remove(user);
                userService.sendEmail(user, message);
            });
        });
    }

    @Scheduled(fixedDelay = 20000)
    public void schedulerNotSendEmail(){
        notSendEmail.forEach((obj) -> {
            taskExecutor.execute(() -> {
                boolean b = userService.sendEmail((User) obj[0], (Message<ResponseMessage>) obj[1]);
                if (b)
                    notSendEmail.remove(obj);
            });
        });
    }
}
