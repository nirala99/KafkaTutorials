package com.nirala.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nirala.kafka.model.Tutorial;
import com.nirala.kafka.producer.SpringBootKafkaProducer;

@RestController
public class SpringBootKafkaRestController {

    @Autowired
    SpringBootKafkaProducer springBootKafkaProducer;

    @GetMapping(value = "/send/{message}")
    public void send(@PathVariable String message) {
        springBootKafkaProducer.sendMessage(message);
    }

    @PostMapping(value = "/send")
    public void send(@RequestBody Tutorial tutorial) {
        springBootKafkaProducer.sendMessage(tutorial);
    }

}
