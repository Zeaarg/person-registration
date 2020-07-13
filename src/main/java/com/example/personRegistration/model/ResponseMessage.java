package com.example.personRegistration.model;

public class ResponseMessage {

    private MessageId messageId;

    private boolean approved;

    public ResponseMessage() {
    }

    public ResponseMessage(MessageId messageId, boolean approved) {
        this.messageId = messageId;
        this.approved = approved;
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public void setMessageId(MessageId messageId) {
        this.messageId = messageId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
