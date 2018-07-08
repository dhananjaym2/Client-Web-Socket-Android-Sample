package client.websocket;

import java.util.Date;

class WebSocketMessage {

    private String messageValue;
    private Date messageTime;

    WebSocketMessage(String messageValue, Date messageTime) {
        this.messageValue = messageValue;
        this.messageTime = messageTime;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String messageValue) {
        this.messageValue = messageValue;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }
}