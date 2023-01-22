package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class SimpleProducer {

    private final static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);
    private final static String TOPIC_NAME = "test2";
    private final static String BOOTSTRAP_SERVERS = "3.39.218.49:9092";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

//        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue);
        ProducerRecord<String, String> record = new ProducerRecord<>("test2", "Pangyo","23");
        producer.send(record, new ProducerCallback());
        producer.flush();
        producer.close();
    }
}
