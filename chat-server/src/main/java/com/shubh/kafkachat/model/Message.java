package com.shubh.kafkachat.model;

public class Message {
    private String sender;
    private String code;
    private String content;
    private String timestamp;

    public Message() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Message(String sender, String code, String content) {
        this.sender = sender;
        this.code = code;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
