package com.example.personRegistration.model;

public class Message<T> {

    private MessageId messageId;
    private T message;

    public Message() {
    }

    public Message(T message) {
        this.message = message;
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public void setMessageId(MessageId messageId) {
        this.messageId = messageId;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
