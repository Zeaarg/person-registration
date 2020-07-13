package com.example.personRegistration;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Util {

    @SneakyThrows
    public static void sleep() {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    public static boolean shouldSleep() {
        return new Random().nextInt(10) == 1;
    }

    public static boolean shouldThrowTimeout() {
        return new Random().nextInt(10) == 1;
    }

    public static boolean shouldApproved() {
        return new Random().nextInt(2) == 1;
    }
}
