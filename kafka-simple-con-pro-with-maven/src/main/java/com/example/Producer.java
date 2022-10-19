package com.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Future;

public class Producer {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {
        log.info("Starting Producer...");

        final Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties);

        for (int i = 0; i < 10; i++) {
            String topic = "first-topic";
            String key = "key_" + Integer.toString(i);
            String value = "hello world - " + Integer.toString(i);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
            producer.send(producerRecord, (recordMetadata, err) -> {
                if (err != null)
                    log.error("Failed to send message", err);
                else
                    log.info("Received new metadata. \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Key:" + producerRecord.key() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
            });
        }
        producer.flush();
        producer.close();


    }

}
