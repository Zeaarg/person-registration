package com.example.personRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonRegistrationApplication {

    public static void main(String[] args){
        SpringApplication.run(PersonRegistrationApplication.class, args);
    }

}
