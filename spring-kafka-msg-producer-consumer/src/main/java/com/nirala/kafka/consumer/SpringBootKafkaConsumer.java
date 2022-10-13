package com.nirala.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nirala.kafka.model.Tutorial;

@Service
public class SpringBootKafkaConsumer {

    @KafkaListener(topics = "${topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Tutorial value){
        System.out.println("Message Received :: "+value.toString());
    }
}
