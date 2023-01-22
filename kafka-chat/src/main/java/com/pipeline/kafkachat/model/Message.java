package com.pipeline.kafkachat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private String sender;
    private String content;
    private String timestamp;
}
