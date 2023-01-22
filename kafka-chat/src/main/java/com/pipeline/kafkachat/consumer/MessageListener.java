package com.pipeline.kafkachat.consumer;

import com.pipeline.kafkachat.constant.KafkaConstants;
import com.pipeline.kafkachat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        template.convertAndSend("/topic/group", message);
    }
}
