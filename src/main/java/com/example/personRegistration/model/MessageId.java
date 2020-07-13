package com.example.personRegistration.model;

import java.util.UUID;

public class MessageId {

    private UUID uuid;

    public MessageId(){

    }

    public MessageId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
