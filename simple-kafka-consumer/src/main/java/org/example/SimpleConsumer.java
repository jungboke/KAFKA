package org.example;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

public class SimpleConsumer {
    private final static Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "3.39.218.49:9092";
    private final static String GROUP_ID = "test-group";

    private static KafkaConsumer<String,String> consumer;
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());

        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("{}", record);
                }
            }
        } catch (WakeupException e) {
            logger.warn("wakeup consumer");
        } finally {
            consumer.close();
        }
    }

//    private static class RebalancListener implements ConsumerRebalanceListener {
//
//        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//            logger.warn("Partitions are assigned : " + partitions.toString());
//
//        }
//
//        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
//            logger.warn("Partitions are revoked : " + partitions.toString());
//        }
//    }

    static class ShutdownThread extends Thread {
        public void run() {
            logger.info("Shutdown hook");
            consumer.wakeup();
        }
    }
}
